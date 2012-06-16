package com.taoists.crm.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "contact")
public class Contact extends BaseEntity {

	@Comment("联系人姓名")
	private String name;
	@Comment("身份证")
	private String idCard;
	@Comment("")
	private LocalDate birthday;
	@Comment("籍贯")
	private String nativePlace;
	@Comment("性别")
	private Integer sex;
	@Comment("手机")
	private String mobile;
	@Comment("公司电话")
	private String officePhone;
	@Comment("email")
	private String email;
	@Comment("职务")
	private String position;
	@Comment("类型（1：老板；2：高管；3：业务负责人；4：普通）")
	@Enumerated
	private ContactType contactType;
	@Comment("状态（0：离职；1：在职）")
	private Integer status;
	@Comment("兴趣爱好")
	private String interest;
	@Comment("所属公司ID")
	@ManyToOne
	private Company company;
	@Comment("所在部门")
	private String deptName;
	@Comment("负责部门ID")
	private Long ownerDeptId;
	@Comment("负责员工ID")
	private Long ownerEmployeeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getOwnerDeptId() {
		return ownerDeptId;
	}

	public void setOwnerDeptId(Long ownerDeptId) {
		this.ownerDeptId = ownerDeptId;
	}

	public Long getOwnerEmployeeId() {
		return ownerEmployeeId;
	}

	public void setOwnerEmployeeId(Long ownerEmployeeId) {
		this.ownerEmployeeId = ownerEmployeeId;
	}

}
