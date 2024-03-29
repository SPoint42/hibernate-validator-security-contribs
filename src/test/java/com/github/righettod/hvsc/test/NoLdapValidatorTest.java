package com.github.righettod.hvsc.test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import com.github.righettod.hvsc.annotation.validator.NoLdapValidator;
import com.github.righettod.hvsc.test.vo.SimpleBean;

/**
 * Test cases for class "NoLdapValidator".
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class NoLdapValidatorTest extends BaseTest {

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
		bean.setData10("Hello World");
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
		for (char c : NoLdapValidator.LDAP_FILTER_SPECIAL_CHARACTER_SET.toCharArray()) {
			testId = "NoLdapValidatorTest::testCaseKO[" + i + "]";
			// Create sample bean
			bean = new SimpleBean();
			bean.setData10("(mail=" + c + "gmail.com)");
			// Apply validation
			constraintViolations = VALIDATOR.validate(bean);
			// Validate test
			Assert.assertTrue("TestID=" + testId, !constraintViolations.isEmpty());
			Assert.assertEquals("TestID=" + testId, 1, constraintViolations.size());
			Assert.assertEquals("TestID=" + testId, "data10", constraintViolations.iterator().next().getPropertyPath().toString());
			i++;
		}
	}

	/**
	 * Test case for the detection of the NULL byte that is used to stop string expression.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCaseNullByte() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData10("Hello%00World");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data10", constraintViolations.iterator().next().getPropertyPath().toString());
	}

}
