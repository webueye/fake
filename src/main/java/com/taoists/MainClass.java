package com.taoists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.collect.Lists;
import com.taoists.code.service.CodeHistoryService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
public class MainClass {

	static Logger logger = LoggerFactory.getLogger(MainClass.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
		CodeHistoryService hs = (CodeHistoryService) ctx.getBean("codeHistoryService");
		
//		hs.imp(Lists.newArrayList("a"), "080923067,067,3000,3000,2008-09-26 08:49:44,");
		
		hs.prehandle("", Lists.newArrayList("a"));
		hs.prehandle("", Lists.newArrayList("a"));
		
	}


	static void pl(Object value) {
		System.err.println(value);
	}

}
