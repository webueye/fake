package com.taoists.code.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.BoxCode;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.common.util.StringUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Controller
@RequestMapping(ResultPath.boxCode)
public class BoxCodeController extends CommonController {

	@RequestMapping("/to-bind")
	public String toBind() {
		return Module.code + "/boxcode/box-fake-bind";
	}

	@RequestMapping("/bind")
	public String bind(String codeList, Model model) {
		logger.debug("bind: codeList[{}]", codeList);

		Map<String, String> map = getBoxCodeService().bind(StringUtils.stringTokenizer(codeList));
		if (map.get("boxCodeNotExist") != null) {
			model.addAttribute("boxCodeNotExist", map.get("boxCodeNotExist"));
		} else if (map.get("fakeCodeNotExist") != null) {
			model.addAttribute("codeList", map.get("fakeCodeNotExist"));
		} else {
			model.addAttribute("success", "success");
		}
		return Module.code + "/boxcode/box-fake-bind";
	}

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

	@RequestMapping(value = "/box-group", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String boxGroup(String boxCodeValues) {
		List<BoxCode> boxCodes = getBoxCodeService().findBoxCodes(StringUtils.stringTokenizer(boxCodeValues));

		JsonConfig conf = new JsonConfig();
		conf.setExcludes(new String[] { "", "createDateTime", "lastModifyDateTime" });
		return JSONArray.fromObject(BoxModel.groupByProduct(boxCodes), conf).toString();
	}

	private String forword(ViewName viewName) {
		return forward(Module.code, ResultPath.boxCode, viewName);
	}

}
