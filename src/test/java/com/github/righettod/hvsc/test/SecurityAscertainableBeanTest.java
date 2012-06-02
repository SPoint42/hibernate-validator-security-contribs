package com.github.righettod.hvsc.test;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Assert;
import org.junit.Test;

import com.github.righettod.hvsc.integration.SecurityAscertainableBean;
import com.github.righettod.hvsc.test.vo.SimpleBeanUsingInheritance;

/**
 * Test cases for class "SecurityAscertainableBean".
 * 
 * Perfom validation in bean integrated mode.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class SecurityAscertainableBeanTest {

	/**
	 * Test case for valid case
	 * 
	 */
	@Test
	public void testCaseOK01() {
		// Create sample bean
		SimpleBeanUsingInheritance bean = new SimpleBeanUsingInheritance();
		bean.setData1("Hello <World !!!");
		// Apply validation
		Set<ConstraintViolation<SecurityAscertainableBean<SimpleBeanUsingInheritance>>> constraintViolations = bean.ascertain();
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for valid case
	 * 
	 */
	@Test
	public void testCaseOK02() {
		// Create sample bean
		SimpleBeanUsingInheritance bean = new SimpleBeanUsingInheritance();
		bean.setData1("Hello <World !!!");
		// Validate test
		Assert.assertTrue(bean.isSuccessfulAudit());
	}

	/**
	 * Test case for invalid case
	 * 
	 */
	@Test
	public void testCaseKO01() {
		// Create sample bean
		SimpleBeanUsingInheritance bean = new SimpleBeanUsingInheritance();
		bean.setData1("Hello World/> !!!");
		// Apply validation
		Set<ConstraintViolation<SecurityAscertainableBean<SimpleBeanUsingInheritance>>> constraintViolations = bean.ascertain();
		// Validate test
		Assert.assertTrue(!constraintViolations.isEmpty());
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("data1", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	/**
	 * Test case for invalid case
	 * 
	 */
	@Test
	public void testCaseKO02() {
		// Create sample bean
		SimpleBeanUsingInheritance bean = new SimpleBeanUsingInheritance();
		bean.setData1("Hello World/> !!!");
		// Validate test
		Assert.assertFalse(bean.isSuccessfulAudit());
	}

}
