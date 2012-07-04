package com.taoists.code.entity;

import javax.persistence.Column;
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
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "code_query")
public class CodeQuery extends BaseEntity {

	@Comment("码号")
	@ManyToOne(fetch = FetchType.LAZY)
	private FakeCode fakeCode;
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String codeNo;
	@Comment("查询方式")
	private Integer queryWay;
	@Comment("查询时间")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime queryDateTime;
	@Comment("查询结果")
	private Integer queryResult;
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String userNo;
	@Column(columnDefinition = "varchar(16) DEFAULT ''")
	private String userPhone;

	public FakeCode getFakeCode() {
		return fakeCode;
	}

	public void setFakeCode(FakeCode fakeCode) {
		this.fakeCode = fakeCode;
	}

	public Integer getQueryWay() {
		return queryWay;
	}

	public void setQueryWay(Integer queryWay) {
		this.queryWay = queryWay;
	}

	public DateTime getQueryDateTime() {
		return queryDateTime;
	}

	public void setQueryDateTime(DateTime queryDateTime) {
		this.queryDateTime = queryDateTime;
	}

	public Integer getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(Integer queryResult) {
		this.queryResult = queryResult;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getCodeNo() {
		return codeNo;
	}
}
