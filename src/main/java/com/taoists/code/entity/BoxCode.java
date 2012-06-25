package com.taoists.code.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.base.entity.BoxSpec;
import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Comment("箱码表")
@Table(name = Cons.tablePrefix + "box_code")
public class BoxCode extends BaseEntity {

	@Comment("码号")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String boxCode;
	@Comment("包装箱种类")
	@ManyToOne
	@JoinColumn(name = "box_spec_id")
	private BoxSpec boxSpec;
	@Comment("码生成操作ID")
	@ManyToOne
	@JoinColumn(name = "code_issue_id")
	private CodeIssue codeIssue;
	@Comment("创建公司ID")
	@Column(name = "creation_company_id")
	private Long creationCompanyId;
	@Comment("当前所在公司")
	@Column(name = "storage_company_id")
	private Long storageCompanyId;
	// @Comment("箱容量")
	// private Integer qty;
	@Comment("状态[-2：废除；-1：售完；0：冻结；1：生成；2：入库；3：在途]")
	private BoxCodeStatus status;

	public String getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}

	public BoxSpec getBoxSpec() {
		return boxSpec;
	}

	public void setBoxSpec(BoxSpec boxSpec) {
		this.boxSpec = boxSpec;
	}

	public CodeIssue getCodeIssue() {
		return codeIssue;
	}

	public void setCodeIssue(CodeIssue codeIssue) {
		this.codeIssue = codeIssue;
	}

	public Long getCreationCompanyId() {
		return creationCompanyId;
	}

	public void setCreationCompanyId(Long creationCompanyId) {
		this.creationCompanyId = creationCompanyId;
	}

	public Long getStorageCompanyId() {
		return storageCompanyId;
	}

	public void setStorageCompanyId(Long storageCompanyId) {
		this.storageCompanyId = storageCompanyId;
	}

	public BoxCodeStatus getStatus() {
		return status;
	}

	public void setStatus(BoxCodeStatus status) {
		this.status = status;
	}

}
