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

import com.github.righettod.hvsc.annotation.validator.CheckTextUploadValidator;

/**
 * Define annotation to validate that an uploaded TEXT (not BINARY) file is not a server side script.<br>
 * <b>Annotation must be placed on a string type member representing the path to the temporary file uploaded before business processing be applied !</b>.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * 
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckTextUploadValidator.class)
@Documented
public @interface CheckTextUpload {

	/* Attributes required by the 'Bean Validation API' */

	/** Message that returns the default key for creating error messages in case the constraint is violated. */
	String message() default "{com.github.righettod.hvsc.annotation.checktextupload}";

	/** Allows the specification of validation groups, to which this constraint belongs. */
	Class<?>[] groups() default {};

	/** Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint. This attribute is not used by the API itself. */
	Class<? extends Payload>[] payload() default {};

	/* Annotation specific attributes */

	/**
	 * List of server side technologies to check (default is all technologies known by the annotation).<br>
	 * List of available values (case insensitive):
	 * <ul>
	 * <li>php</li>
	 * <li>asp</li>
	 * <li>aspnet</li>
	 * <li>jsp</li>
	 * <li>ruby</li>
	 * <li>coldfusion</li>
	 * </ul>
	 */
	String[] serverSideTechnologiesChecked() default {};
}
