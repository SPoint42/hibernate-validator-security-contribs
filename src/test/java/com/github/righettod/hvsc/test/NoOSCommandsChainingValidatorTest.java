package com.github.righettod.hvsc.test;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

import com.github.righettod.hvsc.test.vo.SimpleBean;

/**
 * Test cases for class "NoOSCommandsChainingValidator".
 * 
 * Annotation is tested using JSR303 RI as normal API user...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
@SuppressWarnings("static-method")
public class NoOSCommandsChainingValidatorTest {

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
		bean.setData14("ls -rtl /tmp");
		// Apply validation
		Set<ConstraintViolation<SimpleBean>> constraintViolations = VALIDATOR.validate(bean);
		// Validate test
		Assert.assertTrue(constraintViolations.isEmpty());
	}

	/**
	 * Test case for invalid case : Validate special list<br>
	 * For better design i should create one test by command but i create one test for enhance maintainability.
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void testCaseKO() throws Exception {
		SimpleBean bean = null;
		Set<ConstraintViolation<SimpleBean>> constraintViolations = null;
		List<String> cmds = new ArrayList<String>();
		String baseCommand = "ls -rtl /tmp";
		String testId = "";

		// Add tests commands
		cmds.add(baseCommand + ">test.txt");
		cmds.add(baseCommand + ">>test.txt");
		cmds.add(baseCommand + "<test.txt");
		cmds.add(baseCommand + "<<test.txt");
		cmds.add(baseCommand + "|grep test");
		cmds.add(baseCommand + "||grep test");
		cmds.add(baseCommand + "&dir c:\\");
		cmds.add(baseCommand + "&&dir c:\\");
		cmds.add(baseCommand + ";dir c:\\");
		cmds.add(baseCommand + ";;;dir c:\\");
		cmds.add(baseCommand + ",dir c:\\");
		cmds.add(baseCommand + ",,,dir c:\\");
		cmds.add(baseCommand + URLEncoder.encode(">test.txt", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode(">>test.txt", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode("<test.txt", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode("<<test.txt", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode("|grep test", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode("||grep test", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode("&dir c:\\", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode("&&dir c:\\", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode(";dir c:\\", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode(";;;dir c:\\", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode(",dir c:\\", Charset.defaultCharset().name()));
		cmds.add(baseCommand + URLEncoder.encode(",,,dir c:\\", Charset.defaultCharset().name()));
		// Perform tests
		for (String cmd : cmds) {
			testId = "NoOSCommandsChainingValidatorTest::testCaseKO[" + cmd + "]";
			// Create sample bean
			bean = new SimpleBean();
			bean.setData14(cmd);
			// Apply validation
			constraintViolations = VALIDATOR.validate(bean);
			// Validate test
			Assert.assertTrue("TestID=" + testId, !constraintViolations.isEmpty());
			Assert.assertEquals("TestID=" + testId, 1, constraintViolations.size());
			Assert.assertEquals("TestID=" + testId, "data14", constraintViolations.iterator().next().getPropertyPath().toString());
		}
	}

}
