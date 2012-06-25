package com.taoists.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.common.Cons;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "dept")
public class Dept extends BaseEntity {

	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String name;
	private Dept parent;
	// private Integer level;
	// private Integer subCount;
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String deptNo;
	private Integer ordres;
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String telNo;
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String faxNo;
	private Integer recordStatus;
	@ManyToOne
	private Company company;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dept getParent() {
		return parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public Integer getOrdres() {
		return ordres;
	}

	public void setOrdres(Integer ordres) {
		this.ordres = ordres;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Dept [name=" + name + ", parent=" + parent + ", deptNo="
				+ deptNo + ", ordres=" + ordres + ", telNo=" + telNo
				+ ", faxNo=" + faxNo + ", recordStatus=" + recordStatus
				+ ", company=" + company + "]";
	}

}
