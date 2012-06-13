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
@Comment("入库详细信息")
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "warehousing_item")
public class WarehousingItem extends BaseEntity {

	@ManyToOne
	private Warehousing warehousing;
	@ManyToOne
	private Product product;
	private Integer qty;
	private BigDecimal price;
	private BigDecimal subAmount;
	private String memo;

	public Warehousing getWarehousing() {
		return warehousing;
	}

	public void setWarehousing(Warehousing warehousing) {
		this.warehousing = warehousing;
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
