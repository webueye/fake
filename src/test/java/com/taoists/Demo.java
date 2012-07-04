package com.taoists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.FakeCodeService;


/**
 * @author rubys
 * @since 2012-5-31
 */
public class Demo {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
		BoxCodeService boxCodeService = (BoxCodeService) ctx.getBean("boxCodeService");
		FakeCodeService fakeCodeService = (FakeCodeService) ctx.getBean("fakeCodeService");
		

	}

	static void pl(Object value) {
		System.err.println(value);
	}

}
