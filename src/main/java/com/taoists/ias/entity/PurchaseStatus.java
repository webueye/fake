package com.taoists.ias.entity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public enum PurchaseStatus {

	// @Comment("业务状态（1：新建；2：在途；3：收货）")

	create(1, "新建"), inTransit(2, "在途"), cancel(3, "取消"), receive(4, "收货"), closed(5, "关闭");

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
