package com.taoists.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.FakeCode;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@Controller
@RequestMapping(ResultPath.fakeCode)
public class FakeCodeController extends CommonController {

	@RequestMapping
	public String list(FakeCode fakeCode, Page page) {
		logger.debug("list: fakeCode[{}]", fakeCode);
		fakeCodeService.findPage(fakeCode, page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/boxcode")
	public String listByBoxCode(HttpServletRequest request, Page page) {
		search(request, page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		fakeCodeService.findPage(page, filters);
		extractParams(request);
		return forword(ViewName.list);
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		model.addAttribute("fakeCode", fakeCodeService.get(id));
		return forword(ViewName.show);
	}

	private String forword(ViewName viewName) {
		return forward(Module.code, ResultPath.fakeCode, viewName);
	}

}
