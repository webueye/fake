package com.taoists.base.entity;

import com.taoists.common.orm.Comment;
import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
public class Brand extends BaseEntity {

	@Comment("品牌名称")
	private String brandName;
	@Comment("品牌介绍")
	private String brandDesc;
	@Comment("品牌名称:拼音")
	private String brandEnglishName;
	@Comment("品牌官方网站URL")
	private String link;
	@Comment("品牌Logo图片Url")
	private String logoLink;
	@Comment("状态")
	private Integer status;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getBrandEnglishName() {
		return brandEnglishName;
	}

	public void setBrandEnglishName(String brandEnglishName) {
		this.brandEnglishName = brandEnglishName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
