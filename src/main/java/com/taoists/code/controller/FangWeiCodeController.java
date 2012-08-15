package com.taoists.code.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-15
 */
@Controller
@RequestMapping
public class FangWeiCodeController extends CommonController {
	
	

	@Override
	protected String getModule() {
		return Module.code.getName();
	}

}
