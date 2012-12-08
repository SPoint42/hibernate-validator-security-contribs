package com.github.righettod.hvsc.annotation.validator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.CheckBinaryUpload;

import eu.medsea.mimeutil.MimeUtil2;

/**
 * Implementation of the validator performing processing for annotation "CheckBinaryUpload".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class CheckBinaryUploadValidator extends BaseValidator implements ConstraintValidator<CheckBinaryUpload, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckBinaryUploadValidator.class);

	/** List of allowed mime types */
	private List<String> allowedMimeTypes = new ArrayList<String>();

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(CheckBinaryUpload constraintAnnotation) {
		if ((constraintAnnotation.allowedMimeTypes() != null) && (constraintAnnotation.allowedMimeTypes().length > 0)) {
			// Fill mime type list and change case to lower case to become case insensitive....
			for (String mType : constraintAnnotation.allowedMimeTypes()) {
				if (!StringUtils.isEmpty(mType)) {
					this.allowedMimeTypes.add(mType.trim().toLowerCase(Locale.getDefault()));
				}
			}

			// Debug information
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("CheckBinaryUploadValidator['allowedMimeTypes']='{}'", this.allowedMimeTypes);
			}
		} else {
			LOGGER.warn("No allowed mime types has been specified !");
		}

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
			if (!StringUtils.isEmpty(value)) {
				// Take a ref on file and check existence
				File f = new File(value.trim());
				if (!f.exists()) {
					LOGGER.warn("File '{}' do not exists !", f.getAbsolutePath());
					return isValidFlg;
				}
				// Get file mime type using "eu.medsea.mimeutil" API:
				// I create each time an instance of "MimeUtil2" class because
				// I have not found any informations about "MimeUtil2" thread safety...
				MimeUtil2 mimeUtil = new MimeUtil2();
				mimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
				String mType = MimeUtil2.getMostSpecificMimeType(mimeUtil.getMimeTypes(f)).toString();

				// Check Mime type against allowed mime types list
				isValidFlg = this.allowedMimeTypes.contains(StringUtils.defaultString(mType).trim().toLowerCase(Locale.getDefault()));
			}
		}
		catch (Exception e) {
			LOGGER.error("Error during data validation !", e);
			isValidFlg = false;
		}

		return isValidFlg;
	}
}
