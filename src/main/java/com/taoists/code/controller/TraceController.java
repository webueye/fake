package com.taoists.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.BoxCode;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-21
 */
@Controller
@RequestMapping(ResultPath.trace)
public class TraceController extends CommonController {

	@RequestMapping(value = "retention", method = RequestMethod.GET)
	public String list(BoxCode boxCode, Page page) {
		boxCodeService.findPage(boxCode, page);
		return "/code/trace/retention-list";
	}

	@RequestMapping(value = "retention", method = RequestMethod.POST)
	public String retention(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		boxCodeService.findPage(page, filters);
		extractParams(request);
		return "/code/trace/retention-list";
	}

	@RequestMapping(value = "batch")
	public String batchList(HttpServletRequest request, Page page) {
		boxCodeService.batchTrack(page, PropertyFilter.buildFromHttpRequest(request));
		return "/code/trace/batch-list";
	}

	@RequestMapping(value = "batch", method = RequestMethod.POST)
	public String batchListPost(HttpServletRequest request, Page page) {
		boxCodeService.batchTrack(page, PropertyFilter.buildFromHttpRequest(request));
		return "/code/trace/batch-list";
	}

}
