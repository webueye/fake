package com.taoists.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.enums.CodeIssueTypeEnum;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-15
 */
@Controller
@RequestMapping(ResultPath.fangWeiCode)
public class FangWeiCodeController extends CommonController {

	@RequestMapping
	public String list(HttpServletRequest request, Page page) {
		PropertyFilter filter = new PropertyFilter("EQS_codeType", CodeIssueTypeEnum.fangWeiCode.value());
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		filters.add(filter);
		fangWeiCodeService.findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "stick")
	public String stick(HttpServletRequest request, Page page) {
		PropertyFilter filter = new PropertyFilter("EQS_codeType", CodeIssueTypeEnum.stick.value());
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		filters.add(filter);
		fangWeiCodeService.findPage(page, filters);
		extractParams(request);
		return forward("stick-code-list");
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		fangWeiCodeService.findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/stick-search", method = RequestMethod.POST)
	public String stickSearch(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		fangWeiCodeService.findPage(page, filters);
		extractParams(request);
		return forward("stick-code-list");
	}

	@Override
	protected String getModule() {
		return Module.code.getName();
	}

}
