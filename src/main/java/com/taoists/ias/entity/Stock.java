package com.taoists.ias.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.base.entity.Product;
import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Comment("出入库流水表")
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "stock")
public class Stock extends BaseEntity {

	@ManyToOne
	private Product product;
	@ManyToOne
	private Company company;
	@Comment("出库数量")
	private Integer outCount;
	@Comment("入库数量")
	private Integer inCount;
	@Comment("出库金额")
	private BigDecimal outAmount;
	@Comment("出库金额")
	private BigDecimal inAmount;
	@Comment("操作类型")
	private Integer operateType;
	@Comment("单据编号")
	@Column(columnDefinition = "varchar(32) DEFAULT ''")
	private String billNo;
	@Comment("出入库类型")
	private ChangeTypeStatus changeType;

	public static enum ChangeTypeStatus {
		in(1, "入库"), out(2, "出库");
		int code;
		String value;

		ChangeTypeStatus(int code, String value) {
			this.code = code;
			this.value = value;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getOutCount() {
		return outCount;
	}

	public void setOutCount(Integer outCount) {
		this.outCount = outCount;
	}

	public Integer getInCount() {
		return inCount;
	}

	public void setInCount(Integer inCount) {
		this.inCount = inCount;
	}

	public BigDecimal getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(BigDecimal outAmount) {
		this.outAmount = outAmount;
	}

	public BigDecimal getInAmount() {
		return inAmount;
	}

	public void setInAmount(BigDecimal inAmount) {
		this.inAmount = inAmount;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public ChangeTypeStatus getChangeType() {
		return changeType;
	}

	public void setChangeType(ChangeTypeStatus changeType) {
		this.changeType = changeType;
	}

}
