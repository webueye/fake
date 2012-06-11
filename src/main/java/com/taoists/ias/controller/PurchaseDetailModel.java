package com.taoists.ias.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.taoists.base.entity.Product;
import com.taoists.code.entity.BoxCode;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public class PurchaseDetailModel {

	private Product product;
	private Collection<BoxCode> boxCodes;
	
	public static List<PurchaseDetailModel> groupByProduct(List<BoxCode> boxCodes){
		Multimap<Product, BoxCode> map = HashMultimap.create();
		for (BoxCode boxCode : boxCodes) {
			map.put(boxCode.getBoxSpec().getProduct(), boxCode);
		}

		List<PurchaseDetailModel> models = Lists.newArrayList();
		Iterator<Entry<Product, Collection<BoxCode>>> it = map.asMap().entrySet().iterator();
		while (it.hasNext()) {
			Entry<Product, Collection<BoxCode>> entry = it.next();
			PurchaseDetailModel model = new PurchaseDetailModel();
			model.setProduct(entry.getKey());
			model.setBoxCodes(entry.getValue());
			models.add(model);
		}
		return models;
	}

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
