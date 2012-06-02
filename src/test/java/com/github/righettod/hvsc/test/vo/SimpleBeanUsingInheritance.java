package com.github.righettod.hvsc.test.vo;

import com.github.righettod.hvsc.annotation.NoTag;
import com.github.righettod.hvsc.integration.SecurityAscertainableBean;

/**
 * Simple bean to test inheritance integration validation
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class SimpleBeanUsingInheritance extends SecurityAscertainableBean<SimpleBeanUsingInheritance> {

	/** noTag */
	@NoTag
	private String data1 = null;

	/**
	 * Setter : No Tag
	 * 
	 * @param data1 the data7 to set
	 */
	public void setData1(String data1) {
		this.data1 = data1;
	}

	/**
	 * Getter
	 * 
	 * @return the data1
	 */
	public String getData1() {
		return this.data1;
	}

}
