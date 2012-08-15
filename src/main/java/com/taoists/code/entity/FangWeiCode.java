package com.taoists.code.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.taoists.common.Cons;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-15
 */
@Entity
@Table(name = Cons.tablePrefix + "fang_wei_code")
@SuppressWarnings("serial")
public class FangWeiCode extends BaseEntity {

	private String codeNo;
	private DateTime firstQueryDateTime;
	private Integer queryCount;
	private Boolean status;
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeIssue codeIssue;

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public DateTime getFirstQueryDateTime() {
		return firstQueryDateTime;
	}

	public void setFirstQueryDateTime(DateTime firstQueryDateTime) {
		this.firstQueryDateTime = firstQueryDateTime;
	}

	public Integer getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public CodeIssue getCodeIssue() {
		return codeIssue;
	}

	public void setCodeIssue(CodeIssue codeIssue) {
		this.codeIssue = codeIssue;
	}

}
