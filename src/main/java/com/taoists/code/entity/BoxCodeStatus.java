package com.taoists.code.entity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
public enum BoxCodeStatus {

	abolish(-2, "废除"), soldOut(-1, "售完"), freeze(0, "冻结"), generate(1, "生成"), warehousing(2, "入库"), inTransit(3, "在途");

	Integer code;
	String value;

	BoxCodeStatus(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
