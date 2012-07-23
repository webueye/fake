package com.taoists.sys.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.taoists.common.Cons;
import com.taoists.common.orm.entity.BaseEntity;

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

}
