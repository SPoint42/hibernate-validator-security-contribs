package com.github.righettod.hvsc.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.righettod.hvsc.annotation.validator.NoSmtpValidator;

/**
 * Define annotation to validate content of a string, <br>
 * check that the string do not contains any character that can be interpreted by a mail server in a SMTP message.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints"
 * @see "http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#table-spec-constraints"
 * 
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NoSmtpValidator.class)
@Documented
public @interface NoSmtp {

	/* Attributes required by the 'Bean Validation API' */

	/** Message that returns the default key for creating error messages in case the constraint is violated. */
	String message() default "{com.github.righettod.hvsc.annotation.nosmtp}";

	/** Allows the specification of validation groups, to which this constraint belongs. */
	Class<?>[] groups() default {};

	/** Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint. This attribute is not used by the API itself. */
	Class<? extends Payload>[] payload() default {};

	/* Annotation specific attributes */

	/** Flag to specify if the validator must also check the non standart SMTP headers (headers begining with "X-*") presence (default is True) */
	boolean includeNoStandardHeaders() default true;
}
