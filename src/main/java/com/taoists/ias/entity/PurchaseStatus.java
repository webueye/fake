package com.taoists.ias.entity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public enum PurchaseStatus {

	create(1, "新建"), inTransit(2, "在途"), cancel(3, "取消"), receive(4, "收货"), complete(5, "完成");

	int code;
	String value;

	PurchaseStatus(int code, String value) {
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
