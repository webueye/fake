package com.taoists.ias.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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
@Table(name = Cons.tablePrefix + "purchase")
public class Purchase extends BaseEntity {

	@Comment("单据编号")
	private String purchaseNo;
	@Comment("负责供货的供应商ID")
	private Long supplierId;
	@Comment("进行采购的经销商ID")
	@ManyToOne
	@JoinColumn(name = "purchase_company_id")
	private Company purchaseCompany;
	@Comment("业务类型")
	private Integer purchaseType;
	@Comment("业务状态（1：新建；2：在途；3：收货）")
	private PurchaseStatus status;
	@Comment("优惠金额")
	private BigDecimal discountAmount;
	@Comment("优惠后采购订单总额")
	private BigDecimal totalAmount;
	@Comment("是否开发票（0：否；1：是）")
	private Integer hasInvoice;
	@Comment("备注")
	private String memo;
	@Comment("发货人ID")
	private Long deliveryId;
	@Comment("发货人姓名")
	private String deliveryName;
	@Comment("发货时间")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime deliveryDateTime;
	@Comment("收货人ID")
	private Integer arrivalId;
	@Comment("收货人姓名")
	private String arrivalName;
	@Comment("收货时间")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime arrivalDateTime;

	private Long createrId;
	private String creater;
	private Long completerId;
	private String completer;
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime completeDateTime;

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Company getPurchaseCompany() {
		return purchaseCompany;
	}

	public void setPurchaseCompany(Company purchaseCompany) {
		this.purchaseCompany = purchaseCompany;
	}

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

	public PurchaseStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseStatus status) {
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

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
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

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Long getCompleterId() {
		return completerId;
	}

	public void setCompleterId(Long completerId) {
		this.completerId = completerId;
	}

	public String getCompleter() {
		return completer;
	}

	public void setCompleter(String completer) {
		this.completer = completer;
	}

	public DateTime getCompleteDateTime() {
		return completeDateTime;
	}

	public void setCompleteDateTime(DateTime completeDateTime) {
		this.completeDateTime = completeDateTime;
	}

}
