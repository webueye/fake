package com.taoists.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.BoxTrace;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-21
 */
@Controller
@RequestMapping(ResultPath.boxTrace)
public class BoxTraceController extends CommonController {

	@RequestMapping
	public String list(BoxTrace boxTrace, Page page) {
		boxTraceService.findPage(boxTrace, page);
		return forward(ViewName.list);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		boxTraceService.findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	private String forward(ViewName viewName) {
		return forward(Module.code, ResultPath.boxTrace, viewName);
	}

}
