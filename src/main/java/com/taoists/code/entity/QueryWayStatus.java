package com.taoists.code.entity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public enum QueryWayStatus {

	phone(1, "电话"), sms(2, "短信"), site(3, "网站");

	int code;
	String value;

	QueryWayStatus(int code, String value) {
		this.code = code;
		this.value = value;
	}

}
