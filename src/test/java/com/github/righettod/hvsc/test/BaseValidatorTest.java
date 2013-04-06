package com.github.righettod.hvsc.test;

import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;

import com.github.righettod.hvsc.annotation.validator.BaseValidator;

/**
 * Test cases for class "BaseValidator".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class BaseValidatorTest extends BaseTest {

	/** Tested instance */
	private BaseValidator baseValidator = new BaseValidator();

	/**
	 * Test case for decoding method
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testDecoding() throws Exception {
		int x = 1000;
		String text = "Hello World ? = L'éssentiel % | @ [] ; - # _ . * () {} 100 OK";
		// Encode text X times
		String eText = text.toString();
		for (int i = 1; i <= x; i++) {
			eText = URLEncoder.encode(eText, Charset.defaultCharset().name());
		}
		// Call decoding method
		String dText = this.baseValidator.decode(eText, null);
		// Validate test
		Assert.assertTrue(dText.equals(text));
	}

	/**
	 * Test case for check charset method
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCharsetCheck() throws Exception {
		String s = new String("Hello World !!!".getBytes(), Charset.forName("UTF-8"));
		this.baseValidator.checkExpectedCharset(s);
		// If no exception occurs then test is OK...
	}

}
