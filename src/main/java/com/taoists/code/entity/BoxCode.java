package com.taoists.code.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.taoists.base.entity.BoxSpec;
import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.crm.entity.Company;

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
	@JoinColumn(name = "creation_company_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Company creationCompany;
	@Comment("当前所在公司")
	@JoinColumn(name = "storage_company_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Company storageCompany;
	@Comment("状态[-2：废除；-1：售完；0：冻结；1：生成；2：入库；3：在途]")
	private BoxCodeStatus status;
	private Integer statusCode;
	@Comment("生产日期：绑定时、历史数据导入时设定")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentLocalDate")
	private LocalDate produceDate;

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

	public void setCreationCompany(Company creationCompany) {
		this.creationCompany = creationCompany;
	}

	public Company getCreationCompany() {
		return creationCompany;
	}

	public void setStorageCompany(Company storageCompany) {
		this.storageCompany = storageCompany;
	}

	public Company getStorageCompany() {
		return storageCompany;
	}

	public BoxCodeStatus getStatus() {
		return status;
	}

	public void setStatus(BoxCodeStatus status) {
		this.status = status;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setProduceDate(LocalDate produceDate) {
		this.produceDate = produceDate;
	}

	public LocalDate getProduceDate() {
		return produceDate;
	}

}
