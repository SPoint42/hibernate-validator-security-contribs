package com.github.righettod.hvsc.test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import com.github.righettod.hvsc.annotation.validator.NoSmtpValidator;

/**
 * Test cases for class "NoSmtpValidator".
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class NoSmtpValidatorTest {

	/** JSR303 validator */
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Test case for valid case : No special character and check for non standard header
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseOK01() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData11("Hello World");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for valid case : No special character and no check for non standard header
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseOK02() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData12("%0AX-Mailer:victim@somesite.com,target@hissite.com%0AMy Mail Body");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for invalid case : Check include non standard header<br>
	 * For better design i should create one test by header but i create one test for enhance maintainability.
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseKO01() throws Exception {
		SimpleBean bean = null;
		Set<ConstraintViolation<SimpleBean>> constraintViolations = null;
		String testId = "";

		// Check Standard Header
		for (String header : NoSmtpValidator.SMTP_STANDARD_HEADERS) {
			testId = "NoSmtpValidatorTest::testCaseKO for header [" + header + "]";
			// Create sample bean
			bean = new SimpleBean();
			bean.setData11("%0A" + header + "victim@somesite.com,target@hissite.com%0AMy Mail Body");
			// Apply validation
			constraintViolations = VALIDATOR.validate(bean);
			// Validate test
			Assert.assertTrue("TestID=" + testId, !constraintViolations.isEmpty());
			Assert.assertEquals("TestID=" + testId, 1, constraintViolations.size());
			Assert.assertEquals("TestID=" + testId, "data11", constraintViolations.iterator().next().getPropertyPath().toString());
		}

		// Check non Standard Header
		testId = "NoSmtpValidatorTest::testCaseKO for non Standard Header check";
		// Create sample bean
		bean = new SimpleBean();
		bean.setData11("%0AX-Mailer:victim@somesite.com,target@hissite.com%0AMy Mail Body");
		// Apply validation
		constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue("TestID=" + testId, !constraintViolations.isEmpty());
		Assert.assertEquals("TestID=" + testId, 1, constraintViolations.size());
		Assert.assertEquals("TestID=" + testId, "data11", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for invalid case : Check do not include non standard header<br>
	 * For better design i should create one test by header but i create one test for enhance maintainability.
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseKO02() throws Exception {
		SimpleBean bean = null;
		Set<ConstraintViolation<SimpleBean>> constraintViolations = null;
		String testId = "";

		// Check Standard Header
		for (String header : NoSmtpValidator.SMTP_STANDARD_HEADERS) {
			testId = "NoSmtpValidatorTest::testCaseKO for header [" + header + "]";
			// Create sample bean
			bean = new SimpleBean();
			bean.setData12("%0A" + header + "victim@somesite.com,target@hissite.com%0AMy Mail Body");
			// Apply validation
			constraintViolations = VALIDATOR.validate(bean);
			// Validate test
			Assert.assertTrue("TestID=" + testId, !constraintViolations.isEmpty());
			Assert.assertEquals("TestID=" + testId, 1, constraintViolations.size());
			Assert.assertEquals("TestID=" + testId, "data12", constraintViolations.iterator().next().getPropertyPath().toString());
		}

		// Check non Standard Header
		testId = "NoSmtpValidatorTest::testCaseKO for non Standard Header check";
		// Create sample bean
		bean = new SimpleBean();
		bean.setData12("%0AX-Mailer:victim@somesite.com,target@hissite.com%0AMy Mail Body");
		// Apply validation
		constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue("TestID=" + testId, constraintViolations.isEmpty());
	}

}
