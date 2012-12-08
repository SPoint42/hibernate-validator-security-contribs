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

import com.github.righettod.hvsc.annotation.validator.CheckBinaryUploadValidator;

/**
 * Define annotation to validate that an uploaded file have an allowed mime type.<br>
 * <b>Annotation must be placed on a string type member representing the path to the temporary file uploaded before business processing be applied !</b>.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * @see "http://www.webmaster-toolkit.com/mime-types.shtml"
 * 
 * 
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckBinaryUploadValidator.class)
@Documented
public @interface CheckBinaryUpload {

	/* Attributes required by the 'Bean Validation API' */

	/** Message that returns the default key for creating error messages in case the constraint is violated. */
	String message() default "{com.github.righettod.hvsc.annotation.checkbinaryupload}";

	/** Allows the specification of validation groups, to which this constraint belongs. */
	Class<?>[] groups() default {};

	/** Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint. This attribute is not used by the API itself. */
	Class<? extends Payload>[] payload() default {};

	/* Annotation specific attributes */

	/** List of allowed mime types (case insensitive) */
	String[] allowedMimeTypes() default {};
}
