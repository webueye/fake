package com.taoists.ias.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taoists.code.entity.BoxCode;
import com.taoists.common.Cons;
import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-11
 */
@Comment("入库箱码信息")
@SuppressWarnings("serial")
@Entity
@Table(name = Cons.tablePrefix + "warehousing_box")
public class WarehousingBox extends BaseEntity {

	@ManyToOne
	private Warehousing warehousing;
	@ManyToOne
	private BoxCode boxCode;

	public Warehousing getWarehousing() {
		return warehousing;
	}

	public void setWarehousing(Warehousing warehousing) {
		this.warehousing = warehousing;
	}

	public BoxCode getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(BoxCode boxCode) {
		this.boxCode = boxCode;
	}

}
