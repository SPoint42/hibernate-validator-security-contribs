package com.github.righettod.hvsc.annotation.validator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

import com.github.righettod.hvsc.type.ConfigurationJVMProperties;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

/**
 * Base class of all validator implementations.<br>
 * Provides commons features
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class BaseValidator {

	/**
	 * Method to decode a String managing multi encoding.<br>
	 * <br>
	 * <b>Let client application manage case of "%" character that is not start of a special escaped sequence. <br>
	 * By default the JDK implementation throw an exception that here will set the data security validity to FALSE <br>
	 * and then reject the data.</b>
	 * 
	 * @param s String to decode
	 * @param enc Charset to use (if it's not specified then the default charset is used)
	 * @return Decoded String
	 * @throws UnsupportedEncodingException
	 * @see "http://docs.oracle.com/javase/6/docs/api/java/net/URLDecoder.html"
	 */
	public String decode(String s, String enc) throws UnsupportedEncodingException {
		// Manage empty or null String
		if (StringUtils.isEmpty(s)) {
			return s;
		}

		// Decode String
		// --Manage not specified charset
		String charset = enc;
		if (StringUtils.isEmpty(charset)) {
			charset = Charset.defaultCharset().name();
		}
		// --Manage multi encoding
		String tmp = null;
		String value = s;
		do {
			tmp = value;
			value = URLDecoder.decode(value, charset);
		} while (!tmp.equals(value));

		return value;
	}

	/**
	 * Check that the value use the expected charset (if specified)
	 * 
	 * @param s Value to check
	 * @throws Exception
	 * @see "http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html"
	 */
	public void checkExpectedCharset(String s) throws Exception {
		// Manage empty or null String
		if (!StringUtils.isEmpty(s)) {
			// Check if the dedicated JVM property is specified
			if (!StringUtils.isEmpty(System.getProperty(ConfigurationJVMProperties.HIBERNATE_VALIDATOR_SECURITY_CONTRIBS_EXPECTED_CHARSET.name()))) {
				String expectedCharsetName = System.getProperty(ConfigurationJVMProperties.HIBERNATE_VALIDATOR_SECURITY_CONTRIBS_EXPECTED_CHARSET.name()).trim();
				// Detect charset list
				CharsetDetector detector = new CharsetDetector();
				detector.setText(s.getBytes());
				CharsetMatch[] matchs = detector.detectAll();
				// Check expected charset name against detected list
				if ((matchs == null) || (matchs.length == 0)) {
					throw new Exception("Cannot determine data charset list !");
				}
				boolean findExpected = false;
				for (CharsetMatch cm : matchs) {
					if (cm.getName().equalsIgnoreCase(expectedCharsetName)) {
						findExpected = true;
						break;
					}
				}
				if (!findExpected) {
					throw new Exception("Cannot find expected data charset into charset detected !");
				}

			}
		}
	}
}
