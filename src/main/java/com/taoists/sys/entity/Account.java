package com.taoists.sys.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.taoists.common.Cons;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "account")
public class Account extends BaseEntity {

	private String userNo;
	private String nickname;
	private String email;
	private String username;
	private String password;
	private Boolean status;
	private Boolean admin;
	private Boolean sex;
	private String phone;
	private String mobile;
	private String memo;

	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;

	@Type(type = "com.taoists.common.orm.entity.type.PersistentSet")
	private Set<Long> roles;
	@ManyToOne(fetch = FetchType.LAZY)
	private Dept dept;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setRoles(Set<Long> roles) {
		this.roles = roles;
	}

	public Set<Long> getRoles() {
		return roles;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Dept getDept() {
		return dept;
	}
}
