package com.taoists.code.entity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
public enum CodeQueryResult {

	inexistence(-1, "不存在"), query(0, "正品，未查询"), notQuery(1, "正品，已查询");

	CodeQueryResult(int code, String value) {
		this.code = code;
		this.value = value;
	}

	private int code;
	private String value;

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
