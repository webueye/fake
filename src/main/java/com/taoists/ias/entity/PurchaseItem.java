package com.taoists.ias.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.base.entity.Product;
import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-11
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "purchase_item")
public class PurchaseItem extends BaseEntity {

	@Comment("经销商供货信息")
	@ManyToOne
	private Purchase purchase;
	@Comment("产品信息")
	@ManyToOne
	private Product product;
	@Comment("产品数量")
	private Integer qty;
	@Comment("实际到货数量")
	private Integer actualQty;
	@Comment("单价")
	private BigDecimal price;
	@Comment("小计")
	private BigDecimal subAmount;
	@Comment("备注")
	private String memo;

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getActualQty() {
		return actualQty;
	}

	public void setActualQty(Integer actualQty) {
		this.actualQty = actualQty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubAmount() {
		return subAmount;
	}

	public void setSubAmount(BigDecimal subAmount) {
		this.subAmount = subAmount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
