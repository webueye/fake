package com.taoists;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taoists.code.controller.BoxModel;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.service.BoxCodeService;
import com.taoists.common.util.EncodeUtils;
import com.taoists.common.util.StringUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
public class MainClass {

	static Logger logger = LoggerFactory.getLogger(MainClass.class);

	public static void main(String[] args) {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
//		BoxCodeService boxCodeService = (BoxCodeService) ctx.getBean("boxCodeService");
//		
//		List<BoxCode> boxCodes = boxCodeService.findBoxCodes(StringUtils.stringTokenizer("120616000001 120616000002"));

//		JsonConfig conf = new JsonConfig();
//		conf.setExcludes(new String[] {"createDateTime", "lastModifyDateTime"});
//		List<BoxModel> boxModels = BoxModel.groupByProduct(boxCodes);
//		for(BoxModel boxModel: boxModels){
//			for(BoxCode boxCode: boxModel.getBoxCodes()){
//				boxCode.getBoxSpec().setProduct(null);
//			}
//		}
//		
//		pl(JSONArray.fromObject(boxModels, conf).toString());
		
		
		pl(EncodeUtils.md5("admin"));
	}

	static void pl(Object value) {
		System.err.println(value);
	}

}
