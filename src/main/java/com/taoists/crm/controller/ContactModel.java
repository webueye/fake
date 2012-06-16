package com.taoists.crm.controller;

import com.taoists.crm.entity.Contact;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-16
 */
public class ContactModel {

	private Contact corporation;
	private Contact business;

	public Contact getCorporation() {
		return corporation;
	}

	public void setCorporation(Contact corporation) {
		this.corporation = corporation;
	}

	public Contact getBusiness() {
		return business;
	}

	public void setBusiness(Contact business) {
		this.business = business;
	}

}
