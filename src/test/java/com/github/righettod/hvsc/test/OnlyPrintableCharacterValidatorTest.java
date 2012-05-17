package com.github.righettod.hvsc.test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * Test cases for class "OnlyPrintableCharacterValidator".
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class OnlyPrintableCharacterValidatorTest {

	/** JSR303 validator */
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Test case for valid case : No special character
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseOK01() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData8("Hello-World_!!!");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for valid case : Use a '\n' that is specified in allowed character set on annotation
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseOK02() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData8("Hello\nWorld!!!\n");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for valid case : Use '\t','\r',' ','\n' that are specified in allowed character set on annotation
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseOK03() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData9("Hello World\r!!\n!\t");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for invalid case : Use a '\r' that is NOT specified in allowed character set on annotation
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseKO01() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData8("Hello\rWorld!!!\r");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data8", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for invalid case : Use a ' ' that is NOT specified in allowed character set on annotation
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseKO02() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData8("Hello World!!!");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data8", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for invalid case : Use a '\t' that is NOT specified in allowed character set on annotation
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseKO03() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData8("Hello\tWorld!!!\t");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data8", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for invalid case : Use '\t','\r',' ' that are NOT specified in allowed character set on annotation
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseKO04() throws Exception {
		// Create sample bean
		SimpleBean bean = new SimpleBean();
		bean.setData8("Hello World\r!!!\t");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data8", constraintViolations.iterator().next().getPropertyPath().toString());
	}

}
