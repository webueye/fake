package com.taoists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taoists.code.service.BoxCodeService;
import com.taoists.common.bean.Page;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
public class MainClass {

	static Logger logger = LoggerFactory.getLogger(MainClass.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
		BoxCodeService bs = (BoxCodeService) ctx.getBean("boxCodeService");
		
//		bs.batchTrace(null, new Page());
		
	}

	static void pl(Object value) {
		System.err.println(value);
	}

}
