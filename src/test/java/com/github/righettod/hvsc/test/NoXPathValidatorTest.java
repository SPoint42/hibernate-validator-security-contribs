package com.github.righettod.hvsc.test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import com.github.righettod.hvsc.annotation.validator.NoXPathValidator;
import com.github.righettod.hvsc.test.vo.SimpleBean;

/**
 * Test cases for class "NoXPathValidator".
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class NoXPathValidatorTest extends BaseTest {

	/** JSR303 validator */
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Test case for valid case : No special character
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseOK() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData15("HelloWorld");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for invalid case : Validate special list<br>
	 * For better design i should create one test by character but i create one test for enhance maintainability.
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseKO() throws Exception {
		SimpleBean bean = null;
		Set<ConstraintViolation<SimpleBean>> constraintViolations = null;
		String testId = "";
		int i = 1;
		for (char c : NoXPathValidator.XPATH_FILTER_SPECIAL_CHARACTER_SET.toCharArray()) {
			testId = "NoXPathValidatorTest::testCaseKO[" + i + "]";
			// Create sample bean
			bean = new SimpleBean();
			bean.setData15("my " + c + " expression");
			// Apply validation
			constraintViolations = VALIDATOR.validate(bean);
			// Validate test
			Assert.assertTrue("TestID=" + testId, !constraintViolations.isEmpty());
			Assert.assertEquals("TestID=" + testId, 1, constraintViolations.size());
			Assert.assertEquals("TestID=" + testId, "data15", constraintViolations.iterator().next().getPropertyPath().toString());
			i++;
		}

	}

}
