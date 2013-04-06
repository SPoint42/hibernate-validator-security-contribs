package com.github.righettod.hvsc.annotation.validator;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.righettod.hvsc.annotation.CheckTextUpload;

/**
 * Implementation of the validator performing processing for annotation "CheckTextUpload".
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class CheckTextUploadValidator extends BaseValidator implements ConstraintValidator<CheckTextUpload, String> {

	/** Class logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckTextUploadValidator.class);

	/** Configuration of the entire collection of tags known by the annotation implementation */
	private static final Map<String, List<String>> SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION = new HashMap<String, List<String>>();

	/** Configuration of the collection of tags to search for the current annotation instance */
	private Map<String, List<String>> ssTechnologiesTagsInstanceCollection = new HashMap<String, List<String>>();

	/* Initialization of the entire collection of tags known by the annotation implementation */
	static {
		// Define only start tag because we will search only start tag. The reason is that some technologies allow a
		// script to run (or partially run) even if it do have a closing end tag...
		SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION.put("php", Arrays.asList(new String[] { "<?php", "<%", "<?", "<scriptlanguage=php>" }));
		SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION.put("asp", Arrays.asList(new String[] { "<%" }));
		SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION.put("aspnet", Arrays.asList(new String[] { "<%", "<scriptlanguage=server>" }));
		SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION.put("jsp", Arrays.asList(new String[] { "<%" }));
		SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION.put("ruby", Arrays.asList(new String[] { "<%" }));
		SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION.put("coldfusion", Arrays.asList(new String[] { "<cf" }));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(CheckTextUpload constraintAnnotation) {
		// Initialize the collection of tags to search based on user configuration specified on annotation
		if ((constraintAnnotation.serverSideTechnologiesChecked() == null) || (constraintAnnotation.serverSideTechnologiesChecked().length == 0)) {
			this.ssTechnologiesTagsInstanceCollection.putAll(SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION);
		} else {
			// Fill collection of tags to search for the current annotation instance
			String tmp = null;
			for (String sst : constraintAnnotation.serverSideTechnologiesChecked()) {
				tmp = StringUtils.defaultString(sst).trim().toLowerCase(Locale.getDefault());
				if (!SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION.containsKey(tmp)) {
					LOGGER.warn("'{}' is not a valid server side technology name !", sst);
				} else {
					this.ssTechnologiesTagsInstanceCollection.put(tmp, SSTECHNOLOGIES_TAGS_GLOBAL_COLLECTION.get(tmp));
				}
			}
		}

		// Debug information
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("CheckTextUploadValidator['ssTechnologiesTagsInstanceCollection']='{}'", this.ssTechnologiesTagsInstanceCollection);
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
			// Apply check only if value is not empty....
			if (!StringUtils.isEmpty(value)) {
				// Take a ref on file and check existence
				File f = new File(value.trim());
				if (!f.exists()) {
					LOGGER.warn("File '{}' do not exists !", f.getAbsolutePath());
					return isValidFlg;
				}
				// Load file content
				String initialContent = FileUtils.readFileToString(f);
				// Check value charset
				checkExpectedCharset(initialContent);
				// Decode content using default charset
				String decodedContent = decode(initialContent, Charset.defaultCharset().name());
				// Remove parasitic element from decoded content: space, quote, double quote, tabulation
				String finalContent = decodedContent.replaceAll("(\\s|'|\"|\\t|\\v)", "").toLowerCase(Locale.getDefault()).trim();
				// Apply check on final content : Search tags
				for (Map.Entry<String, List<String>> sst : this.ssTechnologiesTagsInstanceCollection.entrySet()) {
					for (String tag : sst.getValue()) {
						if (finalContent.indexOf(tag) != -1) {
							// Quick exit on first tag found
							isValidFlg = false;
							return isValidFlg;
						}
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
