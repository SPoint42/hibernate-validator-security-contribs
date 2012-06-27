package com.github.righettod.hvsc.test;

import org.junit.BeforeClass;

import com.github.righettod.hvsc.type.ConfigurationJVMProperties;

/**
 * Base class for all test cases.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class BaseTest {

	/**
	 * Global initialization
	 */
	@BeforeClass
	public static void globalInit() {
		// Enable Charset checking
		System.setProperty(ConfigurationJVMProperties.HIBERNATE_VALIDATOR_SECURITY_CONTRIBS_EXPECTED_CHARSET.name(), "UTF-8");
	}
}
