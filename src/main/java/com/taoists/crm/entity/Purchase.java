package com.taoists.crm.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "purchase")
public class Purchase extends BaseEntity {

	@Comment("单据编号")
	private String purchaseNo;
	@Comment("负责供货的供应商ID")
	private Integer supplierId;
	@Comment("进行采购的经销商ID")
	private Integer indealerId;
	@Comment("业务类型")
	private Integer purchaseType;
	@Comment("业务状态（1：新建；2：在途；3：收货）")
	private Integer status;
	@Comment("优惠金额")
	private BigDecimal discountAmount;
	@Comment("优惠后采购订单总额")
	private BigDecimal totalAmount;
	@Comment("是否开发票（0：否；1：是）")
	private Integer hasInvoice;
	@Comment("备注")
	private String memo;
	@Comment("发货人ID")
	private Integer deliveryId;
	@Comment("发货人姓名")
	private Integer deliveryName;
	@Comment("发货时间")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime deliveryDateTime;
	@Comment("收货人ID")
	private Integer arrivalId;
	@Comment("收入货人姓名")
	private String arrivalName;
	@Comment("收入时间")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime arrivalDateTime;

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getIndealerId() {
		return indealerId;
	}

	public void setIndealerId(Integer indealerId) {
		this.indealerId = indealerId;
	}

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getHasInvoice() {
		return hasInvoice;
	}

	public void setHasInvoice(Integer hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Integer getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(Integer deliveryName) {
		this.deliveryName = deliveryName;
	}

	public DateTime getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(DateTime deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public Integer getArrivalId() {
		return arrivalId;
	}

	public void setArrivalId(Integer arrivalId) {
		this.arrivalId = arrivalId;
	}

	public String getArrivalName() {
		return arrivalName;
	}

	public void setArrivalName(String arrivalName) {
		this.arrivalName = arrivalName;
	}

	public DateTime getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(DateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	@Override
	public String toString() {
		return "Purchase [purchaseNo=" + purchaseNo + ", supplierId="
				+ supplierId + ", indealerId=" + indealerId + ", purchaseType="
				+ purchaseType + ", status=" + status + ", discountAmount="
				+ discountAmount + ", totalAmount=" + totalAmount
				+ ", hasInvoice=" + hasInvoice + ", memo=" + memo
				+ ", deliveryId=" + deliveryId + ", deliveryName="
				+ deliveryName + ", deliveryDateTime=" + deliveryDateTime
				+ ", arrivalId=" + arrivalId + ", arrivalName=" + arrivalName
				+ ", arrivalDateTime=" + arrivalDateTime + "]";
	}

}
