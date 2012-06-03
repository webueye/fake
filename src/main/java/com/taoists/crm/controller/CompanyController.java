package com.taoists.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.base.entity.DictCode;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.crm.controller.path.ResultPath;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Controller
@RequestMapping(ResultPath.company)
public class CompanyController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Company company, Page page) {
		getCompanyService().findPage(company, page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		getCompanyService().findPage(page, filters);
		extractParams(request);
		return forword(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		initDict(model);
		return forword(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Company company) {
		logger.debug("create: company[{}]", company);
		getCompanyService().save(company);
		return redirect(ResultPath.company);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		initDict(model);
		return forword(ViewName.edit);
	}

	@RequestMapping("/state/{id}")
	public String state(@PathVariable long id) {
		logger.debug("state: id[{}]", id);
		Company company = getCompanyService().get(id);
		company.setStatus(!BooleanUtils.isTrue(company.getStatus()));
		getCompanyService().update(company);
		return redirect(ResultPath.company);
	}

	@RequestMapping(value = "/update/{dictCategory.id}", method = RequestMethod.POST)
	public String update(Company company) {
		logger.debug("update: company[{}]", company);
		getCompanyService().saveOrUpdate(company);
		return redirect(ResultPath.company);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("remove: id[{}]", id);
		getCompanyService().delete(id);
		return redirect(ResultPath.company);
	}

	@ModelAttribute("company")
	public Company getCompany(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getCompany: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new Company();
		}
		return getCompanyService().get(id);
	}

	private void initDict(Model model) {
		model.addAttribute(DictCode.companyType.concat("s"), getDataDictService().findDataDictByCategoryCode(DictCode.companyType));
		model.addAttribute(DictCode.companyNature.concat("s"), getDataDictService().findDataDictByCategoryCode(DictCode.companyNature));
		model.addAttribute(DictCode.saleForm.concat("s"), getDataDictService().findDataDictByCategoryCode(DictCode.saleForm));
		model.addAttribute(DictCode.saleRegion.concat("s"), getDataDictService().findDataDictByCategoryCode(DictCode.saleRegion));
	}

	private String forword(ViewName viewName) {
		return forward(Module.crm, ResultPath.company, viewName);
	}

}
