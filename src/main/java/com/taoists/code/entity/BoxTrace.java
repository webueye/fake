package com.taoists.code.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@Comment("包装箱物流轨迹表")
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "box_trace")
public class BoxTrace extends BaseEntity {

	@Comment("包箱")
	@ManyToOne
	@JoinColumn(name = "box_code_id")
	private BoxCode boxCode;
	@Comment("记录时间")
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime traceDateTime;
	@Comment("事件类型")
	private Integer eventType;
	@Comment("关联公司")
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;

	public BoxCode getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(BoxCode boxCode) {
		this.boxCode = boxCode;
	}

	public DateTime getTraceDateTime() {
		return traceDateTime;
	}

	public void setTraceDateTime(DateTime traceDateTime) {
		this.traceDateTime = traceDateTime;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "BoxTrace [boxCode=" + boxCode + ", traceDateTime=" + traceDateTime + ", eventType=" + eventType + ", company=" + company + "]";
	}

}
