package com.taoists.code.controller;

import java.util.List;

import net.sf.json.JSONArray;

import com.google.common.collect.Lists;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-14
 */
public class BarCodeBean {

	private String pic;
	
	public BarCodeBean(){
		
	}
	
	public BarCodeBean(String pic){
		this.pic = pic;
	}

	public static void main(String[] args) {

		List<String> list = Lists.newArrayList("a", "b", "c");
		p(JSONArray.fromObject(list).toString());

		BarCodeBean b1 = new BarCodeBean();
		BarCodeBean b2 = new BarCodeBean();
		BarCodeBean b3 = new BarCodeBean();
		b1.pic = "http://a.com";
		b2.pic = "http://b.com";
		b3.pic = "http://c.com";

		List<BarCodeBean> beans = Lists.newArrayList(b1, b2, b3);
		p(JSONArray.fromObject(beans).toString());

	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic() {
		return pic;
	}

	static void p(Object value) {
		System.err.println(value);
	}

}
