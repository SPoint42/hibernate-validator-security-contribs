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

import com.github.righettod.hvsc.annotation.validator.NoLdapValidator;

/**
 * Define annotation to validate content of a string, <br>
 * check that the string do not contains any character that can be interpreted in a LDAP filter expression.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * 
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NoLdapValidator.class)
@Documented
public @interface NoLdap {

	/* Attributes required by the 'Bean Validation API' */

	/** Message that returns the default key for creating error messages in case the constraint is violated. */
	String message() default "{com.github.righettod.hvsc.annotation.noldap}";

	/** Allows the specification of validation groups, to which this constraint belongs. */
	Class<?>[] groups() default {};

	/** Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint. This attribute is not used by the API itself. */
	Class<? extends Payload>[] payload() default {};

	/* No Annotation specific attributes */
}
