package com.taoists;

import org.springframework.beans.BeanUtils;


/**
 * @author rubys
 * @since 2012-5-31
 */
public class Demo {

	public static void main(String[] args) {
		UeyeBean ueyeBean = new UeyeBean();
		ueyeBean.setName("ueeyBean");

		UeyeBean u = new UeyeBean();
		u.setPassword("pass");
		
		
		System.out.println(u.getName());
		

	}

}
