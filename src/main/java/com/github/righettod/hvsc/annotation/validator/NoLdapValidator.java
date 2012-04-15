package com.github.righettod.hvsc.annotation.validator;

import java.net.URLDecoder;
import java.nio.charset.Charset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.NoLdap;

/**
 * Implementation of the validator performing processing for annotation "NoLdap".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints"
 * 
 */
public class NoLdapValidator implements ConstraintValidator<NoLdap, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NoLdapValidator.class);

	/** List of special characters used into LDAP filter expression */
	public static final String LDAP_FILTER_SPECIAL_CHARACTER_SET = "()&|=><~*/\\!";

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NoLdap constraintAnnotation) {
		// No specific initialization need
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValidFlg = true;
		try {
			// Apply check only if value is not empty....
			if (!StringUtils.isEmpty(value)) {
				// Step 1 : Decode value using default charset
				String decodedValue = URLDecoder.decode(value, Charset.defaultCharset().name());
				// Step 2 : Check character list
				for (char c : decodedValue.toCharArray()) {
					if (LDAP_FILTER_SPECIAL_CHARACTER_SET.indexOf(c) != -1) {
						isValidFlg = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error during data validation !", e);
		}

		return isValidFlg;
	}
}