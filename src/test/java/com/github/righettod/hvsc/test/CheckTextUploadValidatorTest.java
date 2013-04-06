package com.github.righettod.hvsc.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
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
 * Test cases for class "CheckTextUploadValidator".<br>
 * 
 * Note : Tests cases here focus on testing file content and do not test encoded content <br>
 * because they are dedicated test cases that validate the correct behavior of decoding feature.<br>
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class CheckTextUploadValidatorTest {

	/** JSR303 validator */
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/** Test case working directory */
	private static final File TEST_WORKSPACE = new File("target/test-text-data");

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
		List<String> testFileList = new ArrayList<String>();
		testFileList.add("testtextfile00.txt");
		testFileList.add("testtextfile01-php.txt");
		testFileList.add("testtextfile02-php.txt");
		testFileList.add("testtextfile03-php.txt");
		testFileList.add("testtextfile04-php.txt");
		testFileList.add("testtextfile01-jsp.txt");
		testFileList.add("testtextfile01-asp.txt");
		testFileList.add("testtextfile01-aspnet.txt");
		testFileList.add("testtextfile02-aspnet.txt");
		testFileList.add("testtextfile01-ruby.txt");
		testFileList.add("testtextfile01-cf.txt");
		InputStream is = null;
		OutputStream os = null;
		for (String f : testFileList) {
			try {
				is = "-".getClass().getResourceAsStream("/" + f);
				os = new FileOutputStream(new File(TEST_WORKSPACE, f));
				IOUtils.copy(is, os);
			}
			finally {
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(os);
			}
		}
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a PHP script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadPhp01CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile01-php.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData18(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data18", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a PHP script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadPhp02CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile02-php.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData18(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data18", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a PHP script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadPhp03CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile03-php.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData18(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data18", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a PHP script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadPhp04CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile04-php.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData18(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data18", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a ASP script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadAsp01CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile01-asp.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData19(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data19", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a ASP.Net script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadAspNet01CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile01-aspnet.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData20(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data20", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a ASP.Net script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadAspNet02CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile02-aspnet.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData20(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data20", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a JSP script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadJsp01CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile01-jsp.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData21(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data21", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a Ruby script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadRuby01CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile01-ruby.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData22(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data22", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a invalid file has been uploaded.<br>
	 * Case with a Cold Fusion script.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadColdFusion01CaseKO() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile01-cf.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData23(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertFalse(constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data23", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case in which a valid file has been uploaded.<br>
	 * Apply test on all SS technologies known by annotation.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUploadCaseOK() throws Exception {
		// Create simple bean
		String fPath = TEST_WORKSPACE.getAbsolutePath() + File.separatorChar + "testtextfile00.txt";
		SimpleBean bean = new SimpleBean();
		bean.setData17(fPath);
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

}
