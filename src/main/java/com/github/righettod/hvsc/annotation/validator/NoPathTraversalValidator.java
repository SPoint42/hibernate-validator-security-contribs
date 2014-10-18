package com.github.righettod.hvsc.annotation.validator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.NoPathTraversal;

/**
 * Implementation of the validator performing processing for annotation "NoPathTraversal".<br>
 * <br>
 * Manage Windows and Unix OS type path resolution.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "https://www.owasp.org/index.php/Category:Path_Traversal_Attack"
 * 
 */
public class NoPathTraversalValidator extends BaseValidator implements ConstraintValidator<NoPathTraversal, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(NoPathTraversalValidator.class);

	/** List of path patterns */
	public static final List<String> PATTERNS = new ArrayList<String>();

	static {
		PATTERNS.add("./");
		PATTERNS.add("../");
		PATTERNS.add(".\\");
		PATTERNS.add("..\\");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NoPathTraversal constraintAnnotation) {
		// Not used
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
				String decodedValueLC = decode(value, Charset.defaultCharset().name());
				// Step 2 : Check for patterns presence
				for (String pattern : PATTERNS) {
					if (decodedValueLC.indexOf(pattern) != -1) {
						isValidFlg = false;
						break;
					}
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
