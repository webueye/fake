package com.taoists.base.entity;

import javax.persistence.Entity;
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
@Table(name = Cons.tablePrefix + "brand")
public class Brand extends BaseEntity {

	@Comment("品牌名称:拼音")
	private String brandSpell;
	@Comment("品牌名称")
	private String brandName;
	@Comment("品牌官方网站URL")
	private String link;
	@Comment("品牌Logo图片Url")
	private String logoLink;
	@Comment("品牌介绍")
	private String brandDesc;
	@Comment("状态[false:禁止, true:激活]")
	private Boolean status;
	
	public Brand(){
		
	}
	
	public Brand(Long id){
		setId(id);
	}

	public String getBrandSpell() {
		return brandSpell;
	}

	public void setBrandSpell(String brandSpell) {
		this.brandSpell = brandSpell;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLogoLink() {
		return logoLink;
	}

	public void setLogoLink(String logoLink) {
		this.logoLink = logoLink;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
