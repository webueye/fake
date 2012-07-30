package com.taoists.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.CodeQuery;
import com.taoists.code.entity.CodeQueryResult;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
@Controller
@RequestMapping(ResultPath.codeQuery)
public class CodeQueryController extends CommonController {

	@RequestMapping("fakement")
	public String fakementList(CodeQuery codeQuery, Page page) {
		codeQuery.setQueryResult(CodeQueryResult.inexistence.getCode());
		codeQueryService.findPage(codeQuery, page);
		return "/code/codequery/fakement-list";
	}

	@RequestMapping(value="fakement", method = RequestMethod.POST)
	public String fakementSearch(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		codeQueryService.findPage(page, filters);
		extractParams(request);
		return "/code/codequery/fakement-list";
	}

}
