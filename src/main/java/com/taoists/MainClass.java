package com.taoists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
public class MainClass {

	static Logger logger = LoggerFactory.getLogger(MainClass.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
		ctx.getBean("");
	}

	static void pl(Object value) {
		System.err.println(value);
	}

}
