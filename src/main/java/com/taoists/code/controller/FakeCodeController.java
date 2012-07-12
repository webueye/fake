package com.taoists.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.util.BarCodeUtils;
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

	@RequestMapping(value = "range", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String range(long codeIssueId) {
		return JSONArray.fromObject(fakeCodeService.getPlainCodeRange(codeIssueId)).toString();
	}

	@RequestMapping(value = "gen-bar-code", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String genBarCode(HttpServletRequest request, long codeIssueId, String startCode, String endCode) {
		String realPath = request.getSession().getServletContext().getRealPath(BAR_CODE_PATH);

		List<FakeCode> fakeCodes = fakeCodeService.findFakeCodes(codeIssueId, startCode, endCode);
		List<String> urls = Lists.newArrayList();
		for (FakeCode fakeCode : fakeCodes) {
			urls.add(BAR_CODE_PATH + BarCodeUtils.genBarCode(realPath, fakeCode.getPlainCode()));
		}
		return JSONArray.fromObject(urls).toString();
	}

	public static final String BAR_CODE_PATH = "/barcode";

	private String forword(ViewName viewName) {
		return forward(Module.code, ResultPath.fakeCode, viewName);
	}

}
