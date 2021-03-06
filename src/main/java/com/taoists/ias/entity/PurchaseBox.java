package com.taoists.ias.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.code.entity.BoxCode;
import com.taoists.common.Cons;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-11
 */
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "purchase_box")
public class PurchaseBox extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private Purchase purchase;
	@ManyToOne(fetch = FetchType.LAZY)
	private PurchaseItem purchaseItem;
	@ManyToOne(fetch = FetchType.LAZY)
	private BoxCode boxCode;

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public BoxCode getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(BoxCode boxCode) {
		this.boxCode = boxCode;
	}

	public PurchaseItem getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(PurchaseItem purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

}
