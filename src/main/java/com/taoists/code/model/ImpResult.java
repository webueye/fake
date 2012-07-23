package com.taoists.code.model;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
public class ImpResult {

	public static enum Type {
		box, fake, other
	}

	private String type;
	private String code;
	private String reason;

	public ImpResult(Type type, String code, String reason) {
		this.type = type.name();
		this.code = code;
		this.reason = reason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
