package com.taoists.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
	@Column(columnDefinition = " DEFAULT ''")
	private String compnayNo;
	@Comment("公司名称")
	@Column(columnDefinition = " DEFAULT ''")
	private String companyName;
	@Comment("类型（1：生产商；2：经销商；3：终端客户；4：潜在客户）")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyTypeId")
	private DataDict companyType;
	@Comment("状态（0：禁用；1：启用）")
	private Boolean status;
	@Comment("上级公司")
	private Long parentId;
	@Comment("所性销售区域")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saleRegionId")
	private DataDict saleRegion;
	@Comment("区域码")
	@Column(columnDefinition = " DEFAULT ''")
	private String zoneNo;// TODO
	@Comment("企业性质")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyNatureId")
	private DataDict companyNature;
	@Comment("销售形式")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saleFormId")
	private DataDict saleForm;
	@Comment("企业规模")
	@Column(columnDefinition = " DEFAULT ''")
	private String scale;
	@Comment("仓库面积")
	@Column(columnDefinition = " DEFAULT ''")
	private String depotArea;
	@Comment("员工数")
	@Column(columnDefinition = " DEFAULT ''")
	private String employeeCount;
	@Comment("业务人员人数")
	@Column(columnDefinition = " DEFAULT ''")
	private String businessCount;
	@Comment("公司地址")
	@Column(columnDefinition = " DEFAULT ''")
	private String address;
	@Comment("公司邮编")
	@Column(columnDefinition = " DEFAULT ''")
	private String companyZip;
	@Comment("开户行名称")
	@Column(columnDefinition = " DEFAULT ''")
	private String belongBankName;
	@Comment("户名")
	@Column(columnDefinition = " DEFAULT ''")
	private String bankAccountName;
	@Comment("银行帐号")
	@Column(columnDefinition = " DEFAULT ''")
	private String bankAccountNo;
	@Comment("收货地址")
	@Column(columnDefinition = " DEFAULT ''")
	private String shippingAddress;
	@Comment("收货人")
	@Column(columnDefinition = " DEFAULT ''")
	private String shippingMan;
	@Comment("收货电话")
	@Column(columnDefinition = " DEFAULT ''")
	private String shippingPhone;
	@Comment("备注")
	@Column(columnDefinition = " DEFAULT ''")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bankAccountName == null) ? 0 : bankAccountName.hashCode());
		result = prime * result + ((bankAccountNo == null) ? 0 : bankAccountNo.hashCode());
		result = prime * result + ((belongBankName == null) ? 0 : belongBankName.hashCode());
		result = prime * result + ((businessCount == null) ? 0 : businessCount.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((companyNature == null) ? 0 : companyNature.hashCode());
		result = prime * result + ((companyType == null) ? 0 : companyType.hashCode());
		result = prime * result + ((companyZip == null) ? 0 : companyZip.hashCode());
		result = prime * result + ((compnayNo == null) ? 0 : compnayNo.hashCode());
		result = prime * result + ((depotArea == null) ? 0 : depotArea.hashCode());
		result = prime * result + ((employeeCount == null) ? 0 : employeeCount.hashCode());
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((saleForm == null) ? 0 : saleForm.hashCode());
		result = prime * result + ((saleRegion == null) ? 0 : saleRegion.hashCode());
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((shippingMan == null) ? 0 : shippingMan.hashCode());
		result = prime * result + ((shippingPhone == null) ? 0 : shippingPhone.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((zoneNo == null) ? 0 : zoneNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bankAccountName == null) {
			if (other.bankAccountName != null)
				return false;
		} else if (!bankAccountName.equals(other.bankAccountName))
			return false;
		if (bankAccountNo == null) {
			if (other.bankAccountNo != null)
				return false;
		} else if (!bankAccountNo.equals(other.bankAccountNo))
			return false;
		if (belongBankName == null) {
			if (other.belongBankName != null)
				return false;
		} else if (!belongBankName.equals(other.belongBankName))
			return false;
		if (businessCount == null) {
			if (other.businessCount != null)
				return false;
		} else if (!businessCount.equals(other.businessCount))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyNature == null) {
			if (other.companyNature != null)
				return false;
		} else if (!companyNature.equals(other.companyNature))
			return false;
		if (companyType == null) {
			if (other.companyType != null)
				return false;
		} else if (!companyType.equals(other.companyType))
			return false;
		if (companyZip == null) {
			if (other.companyZip != null)
				return false;
		} else if (!companyZip.equals(other.companyZip))
			return false;
		if (compnayNo == null) {
			if (other.compnayNo != null)
				return false;
		} else if (!compnayNo.equals(other.compnayNo))
			return false;
		if (depotArea == null) {
			if (other.depotArea != null)
				return false;
		} else if (!depotArea.equals(other.depotArea))
			return false;
		if (employeeCount == null) {
			if (other.employeeCount != null)
				return false;
		} else if (!employeeCount.equals(other.employeeCount))
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (saleForm == null) {
			if (other.saleForm != null)
				return false;
		} else if (!saleForm.equals(other.saleForm))
			return false;
		if (saleRegion == null) {
			if (other.saleRegion != null)
				return false;
		} else if (!saleRegion.equals(other.saleRegion))
			return false;
		if (scale == null) {
			if (other.scale != null)
				return false;
		} else if (!scale.equals(other.scale))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (shippingMan == null) {
			if (other.shippingMan != null)
				return false;
		} else if (!shippingMan.equals(other.shippingMan))
			return false;
		if (shippingPhone == null) {
			if (other.shippingPhone != null)
				return false;
		} else if (!shippingPhone.equals(other.shippingPhone))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (zoneNo == null) {
			if (other.zoneNo != null)
				return false;
		} else if (!zoneNo.equals(other.zoneNo))
			return false;
		return true;
	}

}
