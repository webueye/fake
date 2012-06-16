package com.taoists.base.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.common.Cons;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "data_dict")
public class DataDict extends BaseEntity {

	private String code;
	private String name;
	private String memo;
	private Boolean status;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private DataDict parent;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private DictCategory dictCategory;

	public DataDict() {

	}

	public DataDict(Long id) {
		setId(id);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public DataDict getParent() {
		return parent;
	}

	public void setParent(DataDict parent) {
		this.parent = parent;
	}

	public DictCategory getDictCategory() {
		return dictCategory;
	}

	public void setDictCategory(DictCategory dictCategory) {
		this.dictCategory = dictCategory;
	}

}
