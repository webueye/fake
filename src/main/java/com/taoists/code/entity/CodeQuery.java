package com.taoists.code.entity;

import javax.persistence.Entity;
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

	@Comment("码ID")
	private Long codeId;// TODO
	@Comment("码号")
	private String codeNo;
	@Comment("查询方式")
	private Integer queryWay;
	@Comment("查询时间")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime queryDateTime;
	@Comment("查询结果")
	private Integer queryResult;

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
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

}
