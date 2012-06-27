package com.github.righettod.hvsc.annotation.validator;

import java.nio.charset.Charset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.NoOSCommandsChaining;

/**
 * Implementation of the validator performing processing for annotation "NoOSCommandsChaining".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "https://www.owasp.org/index.php/OS_Command_Injection"
 * @see "http://www.microsoft.com/resources/documentation/windows/xp/all/proddocs/en-us/ntcmds_shelloverview.mspx?mfr=true"
 * @see "http://www.cyberciti.biz/tips/run-several-commands-sequence-all-at-once.html"
 * 
 */
public class NoOSCommandsChainingValidator extends BaseValidator implements ConstraintValidator<NoOSCommandsChaining, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NoOSCommandsChainingValidator.class);

	/** List of special characters used by OS shell command to chain commands */
	public static final String CMD_CHAINING_SPECIAL_CHARACTER_SET = "<>|&;,";

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NoOSCommandsChaining constraintAnnotation) {
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
				String decodedValue = decode(value, Charset.defaultCharset().name());
				// Step 2 : Check character list
				for (char c : decodedValue.toCharArray()) {
					if (CMD_CHAINING_SPECIAL_CHARACTER_SET.indexOf(c) != -1) {
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
