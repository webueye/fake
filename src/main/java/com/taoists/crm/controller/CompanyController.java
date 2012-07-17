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
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes("currentAccount")
@RequestMapping(ResultPath.company)
public class CompanyController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Company company, Page page, Model model) {
		company.setParentId(getAccount(model).getCompanyId());
		companyService.findPage(company, page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		companyService.findPage(page, filters);
		extractParams(request);
		return forword(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		initDict(model);
		return forword(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Company company, ContactModel contactModel) {
		logger.debug("create: company[{}], contactModel[{}]", company, contactModel);
		companyService.save(company, contactModel);
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
		Company company = companyService.get(id);
		company.setStatus(!BooleanUtils.isTrue(company.getStatus()));
		companyService.update(company);
		return redirect(ResultPath.company);
	}

	@RequestMapping(value = "/update/{dictCategory.id}", method = RequestMethod.POST)
	public String update(Company company) {
		logger.debug("update: company[{}]", company);
		companyService.clear();
		company.setSaleForm(new DataDict(company.getSaleForm().getId()));
		company.setCompanyType(new DataDict(company.getCompanyType().getId()));
		company.setSaleRegion(new DataDict(company.getSaleRegion().getId()));
		company.setCompanyNature(new DataDict(company.getCompanyNature().getId()));
		companyService.saveOrUpdate(company);
		return redirect(ResultPath.company);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("remove: id[{}]", id);
		companyService.delete(id);
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
		return companyService.get(id);
	}

	private void initDict(Model model) {
		model.addAttribute(DictCode.companyType.concat("s"), dataDictService.findDataDictByCategoryCode(DictCode.companyType));
		model.addAttribute(DictCode.companyNature.concat("s"), dataDictService.findDataDictByCategoryCode(DictCode.companyNature));
		model.addAttribute(DictCode.saleForm.concat("s"), dataDictService.findDataDictByCategoryCode(DictCode.saleForm));
		model.addAttribute(DictCode.saleRegion.concat("s"), dataDictService.findDataDictByCategoryCode(DictCode.saleRegion));
	}

	private String forword(ViewName viewName) {
		return forward(Module.crm, ResultPath.company, viewName);
	}

}
