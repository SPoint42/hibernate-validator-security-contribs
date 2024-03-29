package com.github.righettod.hvsc.test.vo;

import com.github.righettod.hvsc.annotation.CheckBinaryUpload;
import com.github.righettod.hvsc.annotation.CheckContent;
import com.github.righettod.hvsc.annotation.CheckTextUpload;
import com.github.righettod.hvsc.annotation.NoLdap;
import com.github.righettod.hvsc.annotation.NoOSCommandsChaining;
import com.github.righettod.hvsc.annotation.NoPathTraversal;
import com.github.righettod.hvsc.annotation.NoSmtp;
import com.github.righettod.hvsc.annotation.NoTag;
import com.github.righettod.hvsc.annotation.NoXPath;
import com.github.righettod.hvsc.annotation.OnlyPrintableCharacter;

/**
 * Simple bean to test annotation
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class SimpleBean {

	/** only-alpha */
	@CheckContent(whitelistIdentifier = "only-alpha")
	private String data1 = null;

	/** no-special-character */
	@CheckContent(whitelistIdentifier = "no-special-character")
	private String data2 = null;

	/** check on character repetition only */
	@CheckContent(continuousRepetitionLimitationMapJsonExpr = "{\"(\":1,\"-\":1,\".\":3,\"'\":1}")
	private String data3 = null;

	/** with-special-character + check on character repetition only */
	@CheckContent(whitelistIdentifier = "with-special-character", continuousRepetitionLimitationMapJsonExpr = "{\"(\":1,\"-\":1,\".\":3,\"'\":1}")
	private String data4 = null;

	/** only-number with locale specified (bundle for this locale exists) */
	@CheckContent(whitelistIdentifier = "only-number", whitelistLocale = "lu")
	private String data5 = null;

	/** only-number with locale specified (bundle for this locale do not exists) */
	@CheckContent(whitelistIdentifier = "only-number", whitelistLocale = "de")
	private String data6 = null;

	/** noTag */
	@NoTag
	private String data7 = null;

	/** onlyPrintableCharacter */
	@OnlyPrintableCharacter(acceptedNonPrintableCharacterSet = "\n")
	private String data8 = null;

	/** onlyPrintableCharacter */
	@OnlyPrintableCharacter(acceptedNonPrintableCharacterSet = "\n\r\t ")
	private String data9 = null;

	/** noLdap */
	@NoLdap
	private String data10 = null;

	/** noSmtp */
	@NoSmtp
	private String data11 = null;

	/** noSmtp */
	@NoSmtp(includeNoStandardHeaders = false)
	private String data12 = null;

	/** noPathTraversal */
	@NoPathTraversal
	private String data13 = null;

	/** noOSCommandChaining */
	@NoOSCommandsChaining
	private String data14 = null;

	/** noXPath */
	@NoXPath
	private String data15 = null;

	/** checkBinaryUpload */
	@CheckBinaryUpload(allowedMimeTypes = { "Application/MSWord", "Image/pNg", "" })
	private String data16 = null;

	@CheckTextUpload
	private String data17 = null;

	@CheckTextUpload(serverSideTechnologiesChecked = { "php" })
	private String data18 = null;

	@CheckTextUpload(serverSideTechnologiesChecked = { "asp" })
	private String data19 = null;

	@CheckTextUpload(serverSideTechnologiesChecked = { "aspnet" })
	private String data20 = null;

	@CheckTextUpload(serverSideTechnologiesChecked = { "jsp" })
	private String data21 = null;

	@CheckTextUpload(serverSideTechnologiesChecked = { "ruby" })
	private String data22 = null;

	@CheckTextUpload(serverSideTechnologiesChecked = { "coldfusion" })
	private String data23 = null;

	/**
	 * Getter
	 * 
	 * @return the data6
	 */
	public String getData6() {
		return this.data6;
	}

	/**
	 * Setter : only-number with locale specified (bundle for this locale do not exists)
	 * 
	 * @param data6 the data6 to set
	 */
	public void setData6(String data6) {
		this.data6 = data6;
	}

	/**
	 * Getter
	 * 
	 * @return the data5
	 */
	public String getData5() {
		return this.data5;
	}

	/**
	 * Setter : only-number with locale specified (bundle for this locale exists)
	 * 
	 * @param data5 the data5 to set
	 */
	public void setData5(String data5) {
		this.data5 = data5;
	}

	/**
	 * Getter
	 * 
	 * @return the data1
	 */
	public String getData1() {
		return this.data1;
	}

	/**
	 * Setter : only-alpha
	 * 
	 * @param data1 the data1 to set
	 */
	public void setData1(String data1) {
		this.data1 = data1;
	}

	/**
	 * Getter
	 * 
	 * @return the data2
	 */
	public String getData2() {
		return this.data2;
	}

	/**
	 * Setter : no-special-character
	 * 
	 * @param data2 the data2 to set
	 */
	public void setData2(String data2) {
		this.data2 = data2;
	}

	/**
	 * Getter
	 * 
	 * @return the data3
	 */
	public String getData3() {
		return this.data3;
	}

	/**
	 * Setter : check on character repetition only
	 * 
	 * @param data3 the data3 to set
	 */
	public void setData3(String data3) {
		this.data3 = data3;
	}

	/**
	 * Getter
	 * 
	 * @return the data4
	 */
	public String getData4() {
		return this.data4;
	}

	/**
	 * Setter : with-special-character + check on character repetition only
	 * 
	 * @param data4 the data4 to set
	 */
	public void setData4(String data4) {
		this.data4 = data4;
	}

	/**
	 * Getter
	 * 
	 * @return the data7
	 */
	public String getData7() {
		return this.data7;
	}

	/**
	 * Setter : No Tag
	 * 
	 * @param data7 the data7 to set
	 */
	public void setData7(String data7) {
		this.data7 = data7;
	}

	/**
	 * Getter
	 * 
	 * @return the data8
	 */
	public String getData8() {
		return this.data8;
	}

	/**
	 * Setter : Only Printable Character
	 * 
	 * @param data8 the data8 to set
	 */
	public void setData8(String data8) {
		this.data8 = data8;
	}

	/**
	 * Getter
	 * 
	 * @return the data9
	 */
	public String getData9() {
		return this.data9;
	}

	/**
	 * Setter : Only Printable Character
	 * 
	 * @param data9 the data9 to set
	 */
	public void setData9(String data9) {
		this.data9 = data9;
	}

	/**
	 * Getter
	 * 
	 * @return the data10
	 */
	public String getData10() {
		return this.data10;
	}

	/**
	 * Setter : No Ldap
	 * 
	 * @param data10 the data10 to set
	 */
	public void setData10(String data10) {
		this.data10 = data10;
	}

	/**
	 * Getter
	 * 
	 * @return the data11
	 */
	public String getData11() {
		return this.data11;
	}

	/**
	 * Setter : No SMTP
	 * 
	 * @param data11 the data11 to set
	 */
	public void setData11(String data11) {
		this.data11 = data11;
	}

	/**
	 * Getter
	 * 
	 * @return the data12
	 */
	public String getData12() {
		return this.data12;
	}

	/**
	 * Setter = No SMTP without non standard headers check
	 * 
	 * @param data12 the data12 to set
	 */
	public void setData12(String data12) {
		this.data12 = data12;
	}

	/**
	 * Getter
	 * 
	 * @return the data13
	 */
	public String getData13() {
		return this.data13;
	}

	/**
	 * Setter = No Path Traversal
	 * 
	 * @param data13 the data13 to set
	 */
	public void setData13(String data13) {
		this.data13 = data13;
	}

	/**
	 * Getter
	 * 
	 * @return the data14
	 */
	public String getData14() {
		return this.data14;
	}

	/**
	 * Setter = No OS Command Chaining
	 * 
	 * @param data14 the data14 to set
	 */
	public void setData14(String data14) {
		this.data14 = data14;
	}

	/**
	 * Getter
	 * 
	 * @return the data15
	 */
	public String getData15() {
		return this.data15;
	}

	/**
	 * Setter = No XPath
	 * 
	 * @param data15 the data15 to set
	 */
	public void setData15(String data15) {
		this.data15 = data15;
	}

	/**
	 * Getter
	 * 
	 * @return the data16
	 */
	public String getData16() {
		return this.data16;
	}

	/**
	 * Setter = CheckBinaryUpload
	 * 
	 * @param data16 the data16 to set
	 */
	public void setData16(String data16) {
		this.data16 = data16;
	}

	/**
	 * Setter = CheckTextUpload for PHP SS tech
	 * 
	 * @param data18 the data18 to set
	 */
	public void setData18(String data18) {
		this.data18 = data18;
	}

	/**
	 * Setter = CheckTextUpload for ASP SS tech
	 * 
	 * @param data19 the data19 to set
	 */
	public void setData19(String data19) {
		this.data19 = data19;
	}

	/**
	 * Setter = CheckTextUpload for ASP.NET SS tech
	 * 
	 * @param data20 the data20 to set
	 */
	public void setData20(String data20) {
		this.data20 = data20;
	}

	/**
	 * Setter = CheckTextUpload for JSP SS tech
	 * 
	 * @param data21 the data21 to set
	 */
	public void setData21(String data21) {
		this.data21 = data21;
	}

	/**
	 * Setter = CheckTextUpload for RUBY SS tech
	 * 
	 * @param data22 the data22 to set
	 */
	public void setData22(String data22) {
		this.data22 = data22;
	}

	/**
	 * Setter = CheckTextUpload for COLDFUSION SS tech
	 * 
	 * @param data23 the data23 to set
	 */
	public void setData23(String data23) {
		this.data23 = data23;
	}

	/**
	 * Getter
	 * 
	 * @return the data17
	 */
	public String getData17() {
		return this.data17;
	}

	/**
	 * Setter = CheckTextUpload for all SS tech
	 * 
	 * @param data17 the data17 to set
	 */
	public void setData17(String data17) {
		this.data17 = data17;
	}

	/**
	 * Getter
	 * 
	 * @return the data18
	 */
	public String getData18() {
		return this.data18;
	}

	/**
	 * Getter
	 * 
	 * @return the data19
	 */
	public String getData19() {
		return this.data19;
	}

	/**
	 * Getter
	 * 
	 * @return the data20
	 */
	public String getData20() {
		return this.data20;
	}

	/**
	 * Getter
	 * 
	 * @return the data21
	 */
	public String getData21() {
		return this.data21;
	}

	/**
	 * Getter
	 * 
	 * @return the data22
	 */
	public String getData22() {
		return this.data22;
	}

	/**
	 * Getter
	 * 
	 * @return the data23
	 */
	public String getData23() {
		return this.data23;
	}

}
