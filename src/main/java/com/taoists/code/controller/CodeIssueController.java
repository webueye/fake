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

import com.google.common.collect.Lists;
import com.taoists.code.controller.path.ResultPath;
import com.taoists.code.entity.CodeIssue;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@Controller
@RequestMapping(ResultPath.codeIssue)
public class CodeIssueController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(CodeIssue codeIssue, Page page) {
		codeIssueService.findPage(codeIssue, page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		codeIssueService.findPage(page, filters);
		extractParams(request);
		return forword(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		model.addAttribute("boxSpecs", boxSpecService.findAll());
		return forword(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Account account, CodeIssue codeIssue) {
		logger.debug("create: codeIssue[{}]", codeIssue);
		codeIssueService.genCode(codeIssue);
		return redirect(ResultPath.codeIssue);
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
			response.setHeader("Content-Disposition", "attachment; filename=" + getExportFileName("箱码") + ".xls");
			response.addHeader("Content-Length", "" + bytes.length);
			IOUtils.write(bytes, response.getOutputStream());
		} else {
			byte[] bytes = excelService.exportFakeCodes(codeIssue);
			response.setHeader("Content-Disposition", "attachment; filename=" + getExportFileName("防伪码") + ".xls");
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
	
	private String getExportFileName(String name) throws Exception{
		return new String(name.getBytes("UTF-8"), "ISO8859-1");
	}

	private String forword(ViewName viewName) {
		return forward(Module.code, ResultPath.codeIssue, viewName);
	}

}
