package com.taoists.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.taoists.common.Cons;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "dict_category")
public class DictCategory extends BaseEntity {

	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String categoryCode;
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String categoryName;
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String categoryDesc;
	private Long companyId;

	public DictCategory() {

	}

	public DictCategory(Long id) {
		setId(id);
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
