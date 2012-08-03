package com.taoists.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.common.collect.Lists;
import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.CodeIssue;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@Controller
@SessionAttributes("currentAccount")
@RequestMapping(ResultPath.codeIssue)
public class CodeIssueController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(CodeIssue codeIssue, Page page) {
		codeIssueService.findPage(codeIssue, page);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		codeIssueService.findPage(page, filters);
		extractParams(request);

		if ("true".equals(request.getParameter("filter_EQB_codeType"))) {
			return Module.code.getName() + "/codeissue/box-code-gen-list";
		} else {
			return Module.code.getName() + "/codeissue/fake-code-gen-list";
		}
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		model.addAttribute("boxSpecs", boxSpecService.findAll());
		return forward(ViewName.insert);
	}

	@RequestMapping("/box-code-gen")
	public String boxCodeGen(Model model) {
		model.addAttribute("boxSpecs", boxSpecService.findAll());
		return Module.code.getName() + "/codeissue/box-code-gen";
	}

	@RequestMapping("/box-code-gen-list")
	public String boxCodeGenList(Page page) {
		codeIssueService.findDatas("codeType", true, page);
		return Module.code.getName() + "/codeissue/box-code-gen-list";
	}

	@RequestMapping("/fake-code-gen")
	public String fakeCodeGen(Model model) {
		model.addAttribute("boxSpecs", boxSpecService.findAll());
		return Module.code.getName() + "/codeissue/fake-code-gen";
	}

	@RequestMapping("/fake-code-gen-list")
	public String fakeCodeGenList(Page page) {
		codeIssueService.findDatas("codeType", false, page);
		return Module.code.getName() + "/codeissue/fake-code-gen-list";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Model model, CodeIssue codeIssue) {
		logger.debug("create: codeIssue[{}]", codeIssue);
		codeIssue.setCreationCompany(new Company(getAccount(model).getCompany().getId()));
		codeIssueService.genCode(codeIssue);
		if (codeIssue.getCodeType()) {
			return redirect(ResultPath.codeIssue + "/box-code-gen-list");
		} else {
			return redirect(ResultPath.codeIssue + "/fake-code-gen-list");
		}
	}

	@RequestMapping("/code/{codeIssue.id}")
	public String codeList(@ModelAttribute("codeIssue") CodeIssue codeIssue, Page page) {
		if (codeIssue.getCodeType()) {
			boxCodeService.findDatas("codeIssue.id", codeIssue.getId(), page);
			return "/code/codeissue/box-code-list";
		} else {
			fakeCodeService.findDatas("codeIssue.id", codeIssue.getId(), page);
			return "/code/codeissue/fake-code-list";
		}
	}

	@RequestMapping(value = "/code-search/{id}", method = RequestMethod.POST)
	public String codeSearch(HttpServletRequest request, @PathVariable long id, CodeIssue codeIssue, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		extractParams(request);
		codeIssue = codeIssueService.get(id);
		request.setAttribute("codeIssue", codeIssue);
		if (codeIssue.getCodeType()) {
			boxCodeService.findPage(page, filters);
			return "/code/codeissue/box-code-list";
		} else {
			fakeCodeService.findPage(page, filters);
			return "/code/codeissue/fake-code-list";
		}
	}

	@RequestMapping("/export/{codeIssue.id}")
	public void export(@ModelAttribute("codeIssue") CodeIssue codeIssue, HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream; charset=UTF-8");
		if (codeIssue.getCodeType()) {
			byte[] bytes = excelService.exportBoxCodes(codeIssue);
			response.setHeader("Content-Disposition", "attachment; filename=" + getExportFileName(boxCodeFileName));
			response.addHeader("Content-Length", "" + bytes.length);
			IOUtils.write(bytes, response.getOutputStream());
		} else {
			byte[] bytes = excelService.exportFakeCodes(codeIssue);
			response.setHeader("Content-Disposition", "attachment; filename=" + getExportFileName(fakeCodeFileName));
			response.addHeader("Content-Length", "" + bytes.length);
			IOUtils.write(bytes, response.getOutputStream());
		}
	}

	@ModelAttribute("codeIssue")
	public CodeIssue getCodeIssue(HttpServletRequest request) {
		addMethod(Lists.newArrayList("code", "export"));
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getCodeIssue: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new CodeIssue();
		}
		return codeIssueService.get(id);
	}

	private String getExportFileName(String name) throws Exception {
		return new String(name.getBytes("GBK"), "ISO8859-1");
	}

	private String forward(ViewName viewName) {
		return forward(Module.code, ResultPath.codeIssue, viewName);
	}

	private static final String boxCodeFileName = "箱码.xls";
	private static final String fakeCodeFileName = "防伪码.xls";

}
