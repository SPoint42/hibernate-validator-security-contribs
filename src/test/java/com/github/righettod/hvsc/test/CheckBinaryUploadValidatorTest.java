package com.github.righettod.hvsc.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.righettod.hvsc.test.vo.SimpleBean;

/**
 * Test cases for class "CheckBinaryUploadValidator".
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class CheckBinaryUploadValidatorTest {

	/** JSR303 validator */
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/** Test case working directory */
	private static final File TEST_WORKSPACE = new File("target/test-binary-data");

	/** Extension of samples files */
	private static final String[] TEST_FILE_EXTENSIONS = { "pdf", "doc", "docx", "xls", "xlsx", "png", "jpg", "gif" };

	/** Base name of the sample test file */
	private static final String TEST_FILE_BASE_NAME = "testfile";

	/**
	 * Initialize test cases environment.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@BeforeClass
	public static void initTestEnv() throws Exception {
		if (!TEST_WORKSPACE.exists()) {
			TEST_WORKSPACE.mkdirs();
		}
		InputStream is = null;
		OutputStream os = null;
		for (String ext : TEST_FILE_EXTENSIONS) {
			try {
				is = "-".getClass().getResourceAsStream("/" + TEST_FILE_BASE_NAME + "." + ext);
				// Use generic extension in order to ensure that validator impl. do not use file extension BUT file content stream to find mime type !
				os = new FileOutputStream(new File(TEST_WORKSPACE, ext + "-" + TEST_FILE_BASE_NAME + ".tmp"));
				IOUtils.copy(is, os);
			}
			finally {
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
			}
		}
	}

	/**
	 * Test case in which a valid file has been uploded.<br>
	 * Case of a Word DOC file.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpload01CaseOK() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "doc-" + TEST_FILE_BASE_NAME + ".tmp";
		SimpleBean bean = new SimpleBean();
		bean.setData16(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case in which a valid file has been uploded.<br>
	 * Case of a Image PNG file.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpload02CaseOK() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "png-" + TEST_FILE_BASE_NAME + ".tmp";
		SimpleBean bean = new SimpleBean();
		bean.setData16(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case in which a invalid file has been uploded.<br>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpload01CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "pdf-" + TEST_FILE_BASE_NAME + ".tmp";
		SimpleBean bean = new SimpleBean();
		bean.setData16(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data16", constraintViolations.iterator().next().getPropertyPath().toString());
	}

}
