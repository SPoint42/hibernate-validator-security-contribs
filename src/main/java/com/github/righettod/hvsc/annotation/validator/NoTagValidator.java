package com.github.righettod.hvsc.annotation.validator;

import java.nio.charset.Charset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.NoTag;

/**
 * Implementation of the validator performing processing for annotation "NoTag".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints"
 * 
 */
public class NoTagValidator extends BaseValidator implements ConstraintValidator<NoTag, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NoTagValidator.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NoTag constraintAnnotation) {
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
				// Step 2 : Check tag presence
				isValidFlg = (decodedValue.indexOf("/>") == -1);
			}
		}
		catch (Exception e) {
			LOGGER.error("Error during data validation !", e);
			isValidFlg = false;
		}

		return isValidFlg;
	}
}
