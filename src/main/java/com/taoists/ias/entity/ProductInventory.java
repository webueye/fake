package com.taoists.ias.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.taoists.base.entity.Product;
import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "product_inventory")
public class ProductInventory extends BaseEntity {

	@Comment("产品ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	@Comment("公司")
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	@Comment("当前可用库存")
	private Integer availableQty;
	@Comment("库存高位")
	private Integer maxQty;
	@Comment("库存低位")
	private Integer safeQty;
	@Comment("上次盘点时间")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime lastCheckDateTime;
	@Comment("盘点周期：单位： 天")
	private Integer checkInterval;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(Integer availableQty) {
		this.availableQty = availableQty;
	}

	public Integer getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(Integer maxQty) {
		this.maxQty = maxQty;
	}

	public Integer getSafeQty() {
		return safeQty;
	}

	public void setSafeQty(Integer safeQty) {
		this.safeQty = safeQty;
	}

	public DateTime getLastCheckDateTime() {
		return lastCheckDateTime;
	}

	public void setLastCheckDateTime(DateTime lastCheckDateTime) {
		this.lastCheckDateTime = lastCheckDateTime;
	}

	public Integer getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(Integer checkInterval) {
		this.checkInterval = checkInterval;
	}

}
