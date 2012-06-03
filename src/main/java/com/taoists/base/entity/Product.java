package com.taoists.base.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
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
	private String name;
	@Comment("货号")
	private String productNo;
	@Comment("货号，备用")
	private String productNoExt;
	@Comment("条形码")
	private String barCode;
	@Comment("产吕描述")
	private String productDesc;
	@Comment("状态")
	private Boolean status;
	@Comment("品牌")
	private Brand brand;
	@Comment("市场价")
	private BigDecimal marketPrice;
	@Comment("保质期")
	private Integer retentioPeriod;
	@Comment("拼音")
	private String productEnglishName;
	@Comment("单位")
	private String unit;
	@Comment("规格型号")
	private String spec;
	@Comment("原产地")
	private String origin;
	@Comment("备注")
	private String memo;
	@Comment("记录状态")
	private Integer recordStatus;

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getProductEnglishName() {
		return productEnglishName;
	}

	public void setProductEnglishName(String productEnglishName) {
		this.productEnglishName = productEnglishName;
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

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", name=" + name
				+ ", productNoExt=" + productNoExt + ", barCode=" + barCode
				+ ", productDesc=" + productDesc + ", status=" + status
				+ ", brand=" + brand + ", marketPrice=" + marketPrice
				+ ", retentioPeriod=" + retentioPeriod
				+ ", productEnglishName=" + productEnglishName + ", unit="
				+ unit + ", spec=" + spec + ", origin=" + origin + ", memo="
				+ memo + ", recordStatus=" + recordStatus + "]";
	}

}
