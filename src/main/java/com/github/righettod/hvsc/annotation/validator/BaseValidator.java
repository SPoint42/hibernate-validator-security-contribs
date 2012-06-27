package com.github.righettod.hvsc.annotation.validator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

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
}
