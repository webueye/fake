//package com.taoists;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.taoists.sys.service.AccountService;
//
///**
// * @author rubys@vip.qq.com
// * @since 2012-5-28
// */
//public class AccountServiceTest {
//	
//	Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Test
//	public void test() {
//
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
//
//		AccountService accountService = (AccountService) ctx
//				.getBean("accountService");
//
//		logger.debug("---", accountService);
//
//	}
//
//}
