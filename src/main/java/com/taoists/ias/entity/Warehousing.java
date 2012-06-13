package com.taoists.ias.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.crm.entity.Company;
import com.taoists.sys.entity.Account;

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
	@ManyToOne
	private Company company;
	private String memo;
	// private Long operatorId;
	// private String operator;
	@ManyToOne
	private Account operator;
	@Type(type = "com.taoists.common.orm.entity.type.PersistentDateTime")
	private DateTime warehousingDateTime;

	public String getWarehousingNo() {
		return warehousingNo;
	}

	public void setWarehousingNo(String warehousingNo) {
		this.warehousingNo = warehousingNo;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Account getOperator() {
		return operator;
	}

	public void setOperator(Account operator) {
		this.operator = operator;
	}

	public DateTime getWarehousingDateTime() {
		return warehousingDateTime;
	}

	public void setWarehousingDateTime(DateTime warehousingDateTime) {
		this.warehousingDateTime = warehousingDateTime;
	}

}
