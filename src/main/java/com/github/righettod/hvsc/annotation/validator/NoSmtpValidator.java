package com.github.righettod.hvsc.annotation.validator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.NoSmtp;

/**
 * Implementation of the validator performing processing for annotation "NoSmtp".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "http://forum.unifiedemail.net/default.aspx?g=posts&t=68"
 * 
 */
public class NoSmtpValidator extends BaseValidator implements ConstraintValidator<NoSmtp, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NoSmtpValidator.class);

	/** List of standards SMTP headers */
	public static final List<String> SMTP_STANDARD_HEADERS = new ArrayList<String>();

	/** Flag to specify if the validator must also check the non standart SMTP headers (headers begining with "X-*") presence (default is True) */
	private boolean includeNoStandardHeaders = true;

	static {
		SMTP_STANDARD_HEADERS.add("From:");
		SMTP_STANDARD_HEADERS.add("To:");
		SMTP_STANDARD_HEADERS.add("Subject:");
		SMTP_STANDARD_HEADERS.add("Date:");
		SMTP_STANDARD_HEADERS.add("Message-ID:");
		SMTP_STANDARD_HEADERS.add("Bcc:");
		SMTP_STANDARD_HEADERS.add("Cc:");
		SMTP_STANDARD_HEADERS.add("Content-Type:");
		SMTP_STANDARD_HEADERS.add("In-Reply-To:");
		SMTP_STANDARD_HEADERS.add("Precedence:");
		SMTP_STANDARD_HEADERS.add("Received:");
		SMTP_STANDARD_HEADERS.add("References:");
		SMTP_STANDARD_HEADERS.add("Reply-To:");
		SMTP_STANDARD_HEADERS.add("Sender:");
		SMTP_STANDARD_HEADERS.add("Return-Path:");
		SMTP_STANDARD_HEADERS.add("Error-To:");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NoSmtp constraintAnnotation) {
		this.includeNoStandardHeaders = constraintAnnotation.includeNoStandardHeaders();
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
				String decodedValueLC = decode(value, Charset.defaultCharset().name()).toLowerCase(Locale.getDefault());
				// Step 2 : Check for SMTP standard headers presence
				for (String header : SMTP_STANDARD_HEADERS) {
					if (decodedValueLC.indexOf(header.toLowerCase(Locale.getDefault())) != -1) {
						isValidFlg = false;
						break;
					}
				}
				// Step 3 : Check for SMTP non standard headers presence
				// Perform check only if the value is considered valid by previous check
				if (isValidFlg && this.includeNoStandardHeaders) {
					isValidFlg = (decodedValueLC.indexOf("x-") == -1);
				}

			}
		}
		catch (Exception e) {
			LOGGER.error("Error during data validation !", e);
			isValidFlg = false;
		}

		return isValidFlg;
	}
}
