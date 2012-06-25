package com.taoists.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
@Entity
@Comment("包装箱种类表")
@Table(name = Cons.tablePrefix + "box_spec")
public class BoxSpec extends BaseEntity {

	@Comment("名称")
	@Column(columnDefinition = "varchar(64) DEFAULT ''")
	private String specName;
	@Comment("编号")
	@Column(columnDefinition = "varchar(64) DEFAULT ''")
	private String specNo;
	@Comment("产品")
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	@Comment("创建公司")
	private Long creationCompanyId;
	@Comment("容量")
	private Integer capacity;
	@Comment("状态")
	private Boolean status;
	@Comment("备注")
	@Column(columnDefinition = "varchar(255) DEFAULT ''")
	private String memo;

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecNo() {
		return specNo;
	}

	public void setSpecNo(String specNo) {
		this.specNo = specNo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getCreationCompanyId() {
		return creationCompanyId;
	}

	public void setCreationCompanyId(Long creationCompanyId) {
		this.creationCompanyId = creationCompanyId;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "BoxSpec [specName=" + specName + ", specNo=" + specNo + ", product=" + product + ", creationCompanyId=" + creationCompanyId
				+ ", capacity=" + capacity + ", status=" + status + ", memo=" + memo + "]";
	}

}
