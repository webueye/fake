package com.taoists.code.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.taoists.base.entity.Product;
import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.model.BarCodeBean;
import com.taoists.code.model.BoxModel;
import com.taoists.code.util.BarCodeUtils;
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

		Map<String, String> map = boxCodeService.bind(StringUtils.stringTokenizer(codeList));
		if (map.get("boxCodeNotExist") != null) {
			model.addAttribute("boxCodeNotExist", map.get("boxCodeNotExist"));
		} else if (map.get("fakeCodeNotExist") != null) {
			model.addAttribute("codeList", map.get("fakeCodeNotExist"));
		} else {
			model.addAttribute("success", "success");
		}
		return Module.code + "/boxcode/box-fake-bind";
	}

	@RequestMapping("/from-file")
	public String fromFile() {
		return Module.code + "/boxcode/box-fake-from-file";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload-to-bind", method = RequestMethod.POST)
	public String uploadToBind(@RequestParam MultipartFile codeFile, Model model) {
		InputStream inputStream = null;
		try {
			inputStream = codeFile.getInputStream();
			List<String> lines = IOUtils.readLines(inputStream);
			boxCodeService.fromFileToBind(lines);
			model.addAttribute("msg", "success");
		} catch (Exception e) {
			logger.debug("Upload file to binding error[{}]", e.getMessage());
			model.addAttribute("msg", "error");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					logger.debug("Closing input stream error[{}]", e.getMessage());
					model.addAttribute("msg", "error");
				}
			}
		}
		return Module.code + "/boxcode/box-fake-from-file"; 
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(BoxCode boxCode, Page page) {
		logger.debug("list: boxCode[{}]", boxCode);
		boxCodeService.findPage(boxCode, page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		logger.debug("search: page[{}]", page);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		boxCodeService.findPage(page, filters);
		extractParams(request);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/box-group", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String boxGroup(String boxCodeValues) {
		List<BoxCode> boxCodes = boxCodeService.findBoxCodes(StringUtils.stringTokenizer(boxCodeValues));

		JsonConfig conf = new JsonConfig();
		conf.setExcludes(new String[] { "createDateTime", "lastModifyDateTime" });
		List<BoxModel> boxModels = BoxModel.groupByProduct(boxCodes);
		for (BoxModel boxModel : boxModels) {
			for (BoxCode boxCode : boxModel.getBoxCodes()) {
				boxCode.getBoxSpec().setProduct(null);
				boxCode.setCreationCompany(null);
				boxCode.setStorageCompany(null);
				boxCode.getCodeIssue().setCreationCompany(null);
			}
			boxModel.setProduct(new Product(boxModel.getProduct().getProductNo(), boxModel.getProduct().getName()));
		}
		return JSONArray.fromObject(boxModels, conf).toString();
	}

	@RequestMapping(value = "range", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String range(long codeIssueId) {
		return JSONArray.fromObject(boxCodeService.getCodeRange(codeIssueId).get(0)).toString();
	}

	@RequestMapping(value = "gen-bar-code", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String genBarCode(HttpServletRequest request, long codeIssueId, String startCode, String endCode) {
		String realPath = request.getSession().getServletContext().getRealPath(BAR_CODE_PATH);

		List<BoxCode> boxCodes = boxCodeService.findBoxCodes(codeIssueId, startCode, endCode);
		List<BarCodeBean> beans = Lists.newArrayList();
		for (BoxCode boxCode : boxCodes) {
			StringBuilder sb = new StringBuilder(request.getContextPath() + BAR_CODE_PATH);
			sb.append(BarCodeUtils.genBarCode(realPath, boxCode.getBoxCode()));
			beans.add(new BarCodeBean(sb.toString()));
		}
		String json = JSONArray.fromObject(beans).toString();
		logger.debug("Bar code json[{}]", json);
		return json;
	}

	private String forword(ViewName viewName) {
		return forward(Module.code, ResultPath.boxCode, viewName);
	}

	public static final String BAR_CODE_PATH = "/barcode";

}
