package com.taoists.code.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "fake_code")
public class FakeCode extends BaseEntity {

	@Comment("see codeIssue")
	@ManyToOne
	@JoinColumn(name = "code_issue_id")
	private CodeIssue codeIssue;
	@Comment("明码")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String plainCode;
	@Comment("防伪码")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String fakeCode;
//	@Comment("包装箱种类")
//	@ManyToOne
//	@JoinColumn(name = "box_spec_id")
//	private BoxSpec boxSpec;
	@Comment("箱码")
	@ManyToOne
	@JoinColumn(name = "box_code_id")
	private BoxCode boxCode;
	@Comment("查询次数")
	private Integer queryCount;
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime firstQueryDateTime;
	@Comment("查询方式：1电话，2短信，3网站")
	private QueryWayStatus queryWayStatus;
	private Integer queryWayStatusCode;
	@Comment("是否已查询")
	private Boolean status;

	public CodeIssue getCodeIssue() {
		return codeIssue;
	}

	public void setCodeIssue(CodeIssue codeIssue) {
		this.codeIssue = codeIssue;
	}

	public String getPlainCode() {
		return plainCode;
	}

	public void setPlainCode(String plainCode) {
		this.plainCode = plainCode;
	}

	public String getFakeCode() {
		return fakeCode;
	}

	public void setFakeCode(String fakeCode) {
		this.fakeCode = fakeCode;
	}

	public BoxCode getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(BoxCode boxCode) {
		this.boxCode = boxCode;
	}

	public Integer getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}

	public DateTime getFirstQueryDateTime() {
		return firstQueryDateTime;
	}

	public void setFirstQueryDateTime(DateTime firstQueryDateTime) {
		this.firstQueryDateTime = firstQueryDateTime;
	}

	public QueryWayStatus getQueryWayStatus() {
		return queryWayStatus;
	}

	public void setQueryWayStatus(QueryWayStatus queryWayStatus) {
		this.queryWayStatus = queryWayStatus;
	}

	public void setQueryWayStatusCode(Integer queryWayStatusCode) {
		this.queryWayStatusCode = queryWayStatusCode;
	}

	public Integer getQueryWayStatusCode() {
		return queryWayStatusCode;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

//	public BoxSpec getBoxSpec() {
//		return boxSpec;
//	}
//
//	public void setBoxSpec(BoxSpec boxSpec) {
//		this.boxSpec = boxSpec;
//	}

}
