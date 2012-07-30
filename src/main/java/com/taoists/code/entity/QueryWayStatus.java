package com.taoists.code.entity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public enum QueryWayStatus {

	phone(1, "电话"), sms(2, "短信"), site(3, "网站");

	private int code;
	private String value;

	QueryWayStatus(int code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static QueryWayStatus getQueryWayStatus(int code){
		if(phone.getCode() == code){
			return phone;
		}
		if(sms.getCode() == code){
			return sms;
		}
		if(site.getCode() == code){
			return site;
		}
		throw new IllegalArgumentException("Query way code must in [1, 2, 3]");
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
