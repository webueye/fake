package com.taoists.code.entity.enums;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-18
 */
public enum CodeIssueTypeEnum {

	boxCode("boxCode"), fakeCode("fakeCode"), fangWeiCode("fangWeiCode"), stick("stick");

	private String value;

	CodeIssueTypeEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

}
