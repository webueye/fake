package com.taoists.ias.controller;

import java.util.Collection;

import com.taoists.base.entity.Product;
import com.taoists.code.entity.BoxCode;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public class PurchaseDetailModel {

	private Product product;
	private Collection<BoxCode> boxCodes;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Collection<BoxCode> getBoxCodes() {
		return boxCodes;
	}

	public void setBoxCodes(Collection<BoxCode> boxCodes) {
		this.boxCodes = boxCodes;
	}

	public Integer getBoxCount() {
		return boxCodes.size();
	}

	public Integer getTotalCount() {
		Integer totalCount = 0;
		for (BoxCode bc : boxCodes) {
			totalCount += bc.getBoxSpec().getCapacity();
		}
		return totalCount;
	}

}
