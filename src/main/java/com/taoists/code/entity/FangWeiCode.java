package com.taoists.code.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
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
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime firstQueryDateTime;
	private Integer queryCount;
	private Boolean status;
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeIssue codeIssue;

	@Comment("查询方式：1电话，2短信，3网站")
	private Integer queryWayStatus;

	private String codeType;

	public void setQueryWayStatus(Integer queryWayStatus) {
		this.queryWayStatus = queryWayStatus;
	}

	public Integer getQueryWayStatus() {
		return queryWayStatus;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeType() {
		return codeType;
	}

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
