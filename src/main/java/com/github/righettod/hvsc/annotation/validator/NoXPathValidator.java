package com.github.righettod.hvsc.annotation.validator;

import java.nio.charset.Charset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.NoXPath;

/**
 * Implementation of the validator performing processing for annotation "NoXPath".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "https://www.owasp.org/index.php/XPATH_Injection_Java"
 * 
 */
public class NoXPathValidator extends BaseValidator implements ConstraintValidator<NoXPath, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NoXPathValidator.class);

	/** List of special characters used into XPATH filter expression */
	public static final String XPATH_FILTER_SPECIAL_CHARACTER_SET = "()='[] :,*/";

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NoXPath constraintAnnotation) {
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
				// Step 1a : Check value charset
				checkExpectedCharset(value);
				// Step 1b : Decode value using default charset
				String decodedValue = decode(value, Charset.defaultCharset().name());
				// Step 2 : Check character list
				for (char c : decodedValue.toCharArray()) {
					if (XPATH_FILTER_SPECIAL_CHARACTER_SET.indexOf(c) != -1) {
						isValidFlg = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error during data validation !", e);
			isValidFlg = false;
		}

		return isValidFlg;
	}
}
