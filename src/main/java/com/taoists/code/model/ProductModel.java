package com.taoists.code.model;

import java.util.List;

import com.taoists.base.entity.Product;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
public class ProductModel {

	private Product product;
	private List<BatchModel> batchs;

	public int getSize() {
		if (batchs == null) {
			return 0;
		}
		return batchs.size();
	}

	public static class BatchModel {

		private String batch;
		private Integer boxNum;
		private Integer fakeNum;

		public String getBatch() {
			return batch;
		}

		public void setBatch(String batch) {
			this.batch = batch;
		}

		public Integer getBoxNum() {
			return boxNum;
		}

		public void setBoxNum(Integer boxNum) {
			this.boxNum = boxNum;
		}

		public Integer getFakeNum() {
			return fakeNum;
		}

		public void setFakeNum(Integer fakeNum) {
			this.fakeNum = fakeNum;
		}

	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<BatchModel> getBatchs() {
		return batchs;
	}

	public void setBatchs(List<BatchModel> batchs) {
		this.batchs = batchs;
	}

}
