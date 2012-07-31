package com.taoists.sys.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.taoists.common.Cons;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-19
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "role")
public class Role extends BaseEntity {

	private String roleName;
	private String description;
	@Type(type = "com.taoists.common.orm.entity.type.PersistentSet")
	private Set<Long> menus;
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	@Transient
	private String checked;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMenus(Set<Long> menus) {
		this.menus = menus;
	}

	public Set<Long> getMenus() {
		return menus;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getChecked() {
		return checked;
	}
}
