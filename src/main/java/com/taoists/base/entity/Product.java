package com.taoists.base.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "product")
public class Product extends BaseEntity {

	@Comment("名称")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String name;
	@Comment("货号")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String productNo;
	@Comment("货号，备用")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String productNoExt;
	@Comment("条形码")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String barCode;
	@Comment("产吕描述")
	@Column(columnDefinition = "varchar(255) DEFAULT ''")
	private String productDesc;
	@Comment("状态")
	private Boolean status;
	@Comment("产品分类")
	@ManyToOne(fetch = FetchType.LAZY)
	private DataDict productCategory;
	@Comment("品牌")
	@ManyToOne(fetch = FetchType.LAZY)
	private Brand brand;
	@Comment("市场价")
	private BigDecimal marketPrice;
	@Comment("保质期")
	private Integer retentioPeriod;
	@Comment("拼音")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String productSpell;
	@Comment("单位")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String unit;
	@Comment("规格型号")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String spec;
	@Comment("原产地")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String origin;
	@Comment("备注")
	@Column(columnDefinition = "varchar(255) DEFAULT ''")
	private String memo;
	@Comment("记录状态")
	private Integer recordStatus;

	public Product() {

	}

	public Product(Long id) {
		setId(id);
	}

	public Product(String productNo, String name) {
		this.productNo = productNo;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductNoExt() {
		return productNoExt;
	}

	public void setProductNoExt(String productNoExt) {
		this.productNoExt = productNoExt;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public DataDict getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(DataDict productCategory) {
		this.productCategory = productCategory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getRetentioPeriod() {
		return retentioPeriod;
	}

	public void setRetentioPeriod(Integer retentioPeriod) {
		this.retentioPeriod = retentioPeriod;
	}

	public String getProductSpell() {
		return productSpell;
	}

	public void setProductSpell(String productSpell) {
		this.productSpell = productSpell;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

}
