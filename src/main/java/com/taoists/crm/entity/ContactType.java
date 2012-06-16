package com.taoists.crm.entity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-16
 */
public enum ContactType {

	boss(1, "老板"), manager(2, "高管"), business(3, "业务负责人"), general(4, "普通");

	int code;
	String value;

	ContactType(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
