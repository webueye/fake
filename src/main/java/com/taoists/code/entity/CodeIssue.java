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
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "code_issue")
public class CodeIssue extends BaseEntity {

	@Comment("生成码名称")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String issueName;
	@Comment("货品条码")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String barCode;
	@Comment("包装箱种类")
	@ManyToOne
	@JoinColumn(name = "box_spec_id")
	private BoxSpec boxSpec;
	@Comment("生成数量")
	private Integer issueCount;
	@Comment("码长度")
	private Integer codeLength;
	@Comment("备注")
	@Column(columnDefinition = "varchar(255) DEFAULT ''")
	private String memo;
	@Comment("状态")
	private Boolean status;
	@Comment("创建公司ID")
	private Long companyId;
	@Comment("生成码类型: 防伪码/箱码")
	private Boolean codeType;

	@Comment("操作人")
	@ManyToOne
	@JoinColumn(name = "operator_id")
	private Account operator;

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public BoxSpec getBoxSpec() {
		return boxSpec;
	}

	public void setBoxSpec(BoxSpec boxSpec) {
		this.boxSpec = boxSpec;
	}

	public Integer getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(Integer issueCount) {
		this.issueCount = issueCount;
	}

	public Integer getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(Integer codeLength) {
		this.codeLength = codeLength;
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Boolean getCodeType() {
		return codeType;
	}

	public void setCodeType(Boolean codeType) {
		this.codeType = codeType;
	}

	public Account getOperator() {
		return operator;
	}

	public void setOperator(Account operator) {
		this.operator = operator;
	}

}
