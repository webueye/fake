package com.taoists.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.base.entity.DataDict;
import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "company")
public class Company extends BaseEntity {

	@Comment("公司编号")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String compnayNo;
	@Comment("公司名称")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String companyName;
	@Comment("类型（1：生产商；2：经销商；3：终端客户；4：潜在客户）")
	@ManyToOne(fetch = FetchType.LAZY)
	private DataDict companyType;
	@Comment("状态（0：禁用；1：启用）")
	private Boolean status;
	@Comment("上级公司")
	private Long parentId;
	@Comment("所性销售区域")
	@ManyToOne(fetch = FetchType.LAZY)
	private DataDict saleRegion;
	@Comment("区域码")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String zoneNo;// TODO
	@Comment("企业性质")
	@ManyToOne(fetch = FetchType.LAZY)
	private DataDict companyNature;
	@Comment("销售形式")
	@ManyToOne(fetch = FetchType.LAZY)
	private DataDict saleForm;
	@Comment("企业规模")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String scale;
	@Comment("仓库面积")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String depotArea;
	@Comment("员工数")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String employeeCount;
	@Comment("业务人员人数")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String businessCount;
	@Comment("公司地址")
	@Column(columnDefinition = "varchar(128) DEFAULT ''")
	private String address;
	@Comment("公司邮编")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String companyZip;
	@Comment("开户行名称")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String belongBankName;
	@Comment("户名")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String bankAccountName;
	@Comment("银行帐号")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String bankAccountNo;
	@Comment("收货地址")
	@Column(columnDefinition = "varchar(128) DEFAULT ''")
	private String shippingAddress;
	@Comment("收货人")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String shippingMan;
	@Comment("收货电话")
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String shippingPhone;
	@Comment("备注")
	@Column(columnDefinition = "varchar(255) DEFAULT ''")
	private String memo;

	public Company() {

	}

	public Company(Long id) {
		setId(id);
	}

	public String getCompnayNo() {
		return compnayNo;
	}

	public void setCompnayNo(String compnayNo) {
		this.compnayNo = compnayNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyType(DataDict companyType) {
		this.companyType = companyType;
	}

	public DataDict getCompanyType() {
		return companyType;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public DataDict getSaleRegion() {
		return saleRegion;
	}

	public void setSaleRegion(DataDict saleRegion) {
		this.saleRegion = saleRegion;
	}

	public String getZoneNo() {
		return zoneNo;
	}

	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}

	public DataDict getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(DataDict companyNature) {
		this.companyNature = companyNature;
	}

	public DataDict getSaleForm() {
		return saleForm;
	}

	public void setSaleForm(DataDict saleForm) {
		this.saleForm = saleForm;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getDepotArea() {
		return depotArea;
	}

	public void setDepotArea(String depotArea) {
		this.depotArea = depotArea;
	}

	public String getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
	}

	public String getBusinessCount() {
		return businessCount;
	}

	public void setBusinessCount(String businessCount) {
		this.businessCount = businessCount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyZip() {
		return companyZip;
	}

	public void setCompanyZip(String companyZip) {
		this.companyZip = companyZip;
	}

	public String getBelongBankName() {
		return belongBankName;
	}

	public void setBelongBankName(String belongBankName) {
		this.belongBankName = belongBankName;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingMan() {
		return shippingMan;
	}

	public void setShippingMan(String shippingMan) {
		this.shippingMan = shippingMan;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
