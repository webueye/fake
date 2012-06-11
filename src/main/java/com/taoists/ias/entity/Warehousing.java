package com.taoists.ias.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-11
 */
@Comment("入库信息")
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "warehousing")
public class Warehousing extends BaseEntity {

	private String warehousingNo;
	private Long companyId;
	private String memo;
	private Long operatorId;
	private String operator;
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime warehousingDateTime;

	public String getWarehousingNo() {
		return warehousingNo;
	}

	public void setWarehousingNo(String warehousingNo) {
		this.warehousingNo = warehousingNo;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public DateTime getWarehousingDateTime() {
		return warehousingDateTime;
	}

	public void setWarehousingDateTime(DateTime warehousingDateTime) {
		this.warehousingDateTime = warehousingDateTime;
	}

}
