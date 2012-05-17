package com.github.righettod.hvsc.annotation.validator;

import static java.lang.Character.CONNECTOR_PUNCTUATION;
import static java.lang.Character.CURRENCY_SYMBOL;
import static java.lang.Character.DASH_PUNCTUATION;
import static java.lang.Character.DECIMAL_DIGIT_NUMBER;
import static java.lang.Character.ENCLOSING_MARK;
import static java.lang.Character.END_PUNCTUATION;
import static java.lang.Character.FINAL_QUOTE_PUNCTUATION;
import static java.lang.Character.INITIAL_QUOTE_PUNCTUATION;
import static java.lang.Character.LETTER_NUMBER;
import static java.lang.Character.LOWERCASE_LETTER;
import static java.lang.Character.MATH_SYMBOL;
import static java.lang.Character.MODIFIER_LETTER;
import static java.lang.Character.MODIFIER_SYMBOL;
import static java.lang.Character.OTHER_LETTER;
import static java.lang.Character.OTHER_NUMBER;
import static java.lang.Character.OTHER_PUNCTUATION;
import static java.lang.Character.OTHER_SYMBOL;
import static java.lang.Character.START_PUNCTUATION;
import static java.lang.Character.TITLECASE_LETTER;
import static java.lang.Character.UPPERCASE_LETTER;
import static java.lang.Character.getType;

import java.net.URLDecoder;
import java.nio.charset.Charset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.OnlyPrintableCharacter;

/**
 * Implementation of the validator performing processing for annotation "OnlyPrintableCharacter".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints"
 * 
 */
public class OnlyPrintableCharacterValidator implements ConstraintValidator<OnlyPrintableCharacter, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(OnlyPrintableCharacterValidator.class);

	/** List (as char array) of non printable character that are accepted (exception) */
	private String acceptedNonPrintableCharacterSet = null;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(OnlyPrintableCharacter constraintAnnotation) {
		// Avoid NULL String...
		this.acceptedNonPrintableCharacterSet = StringUtils.defaultString(constraintAnnotation.acceptedNonPrintableCharacterSet());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValidFlg = true;
		int t = 0;
		boolean testFlag = false;

		try {
			// Apply check only if value is not empty....
			if (!StringUtils.isEmpty(value)) {
				// Step 1 : Decode value using default charset
				String decodedValue = URLDecoder.decode(value, Charset.defaultCharset().name());
				// Step 2 : Check each character
				for (char c : decodedValue.toCharArray()) {
					// Check if current character is in allowed list
					if (this.acceptedNonPrintableCharacterSet.indexOf(c) != -1) {
						continue;
					}
					// Test current character
					// --Get character unicode type
					t = getType(c);
					// --Check if it's a printable character
					testFlag = (t == CONNECTOR_PUNCTUATION) || (t == CURRENCY_SYMBOL) || (t == DASH_PUNCTUATION) || (t == DECIMAL_DIGIT_NUMBER) || (t == ENCLOSING_MARK) || (t == END_PUNCTUATION)
							|| (t == FINAL_QUOTE_PUNCTUATION) || (t == INITIAL_QUOTE_PUNCTUATION) || (t == LETTER_NUMBER) || (t == LOWERCASE_LETTER) || (t == MATH_SYMBOL) || (t == MODIFIER_LETTER)
							|| (t == MODIFIER_SYMBOL) || (t == OTHER_LETTER) || (t == OTHER_NUMBER) || (t == OTHER_PUNCTUATION) || (t == OTHER_SYMBOL) || (t == START_PUNCTUATION)
							|| (t == TITLECASE_LETTER) || (t == UPPERCASE_LETTER);
					// --Manage previous check result
					if (!testFlag) {
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
