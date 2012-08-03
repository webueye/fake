package com.taoists.ias.entity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-2
 */
public enum PurchaseTypeEnum {

	purchase(2, "采购"), returnedPurchase(6, "退货");

	int code;
	String value;

	PurchaseTypeEnum(int code, String value) {
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
