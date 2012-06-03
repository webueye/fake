package com.taoists.common.controller;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
public enum Module {

	crm("/crm"), base("/base"), code("/code"), ias("/ias");

	String name;

	Module(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
