package com.github.righettod.hvsc.integration;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Base class used to specify that a bean has its members annoted with HVSC security annotations <br>
 * and provide then a generic method to ascertain that its member are valid on a security point of view.
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 * @param <T> Type of the target bean
 * 
 */
public class SecurityAscertainableBean<T> {

	/** JSR303 validator */
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Apply validation on current bean instance
	 * 
	 * @return Set of violated rules
	 */
	public Set<ConstraintViolation<SecurityAscertainableBean<T>>> ascertain() {
		return this.validator.validate(this);
	}

	/**
	 * Apply validation on current bean instance
	 * 
	 * @return TRUE only if no rules are violated
	 */
	public boolean isSuccessfulAudit() {
		return this.validator.validate(this).isEmpty();
	}
}
