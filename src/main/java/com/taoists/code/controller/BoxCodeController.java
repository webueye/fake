package com.taoists.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.BoxCode;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Controller
@RequestMapping(ResultPath.boxCode)
public class BoxCodeController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(BoxCode boxCode, Page page) {
		logger.debug("list: boxCode[{}]", boxCode);
		getBoxCodeService().findPage(boxCode, page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		getBoxCodeService().findPage(page, filters);
		extractParams(request);
		return forword(ViewName.list);
	}

	private String forword(ViewName viewName) {
		return forward(Module.code, ResultPath.boxCode, viewName);
	}

}
