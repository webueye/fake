package com.taoists.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.base.controller.path.ResultPath;
import com.taoists.base.entity.Brand;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-15
 */
@Controller
@RequestMapping(ResultPath.brand)
public class BrandController extends CommonController {

	@RequestMapping
	public String list(Brand brand, Page page) {
		brandService.findPage(page);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		brandService.findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Brand brand) {
		logger.debug("create: brand[{}]", brand);
		brandService.save(brand);
		return redirect(ResultPath.brand);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		return forward(ViewName.edit);
	}

	@RequestMapping(value = "/update/{brand.id}", method = RequestMethod.POST)
	public String update(Brand brand) {
		logger.debug("update: brand[{}]", brand);
		brandService.saveOrUpdate(brand);
		return redirect(ResultPath.brand);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("destroy: id[{}]", id);
		brandService.delete(id);
		return redirect(ResultPath.brand);
	}

	@ModelAttribute("brand")
	public Brand getBrand(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new Brand();
		}
		return brandService.get(id);
	}

	private String forward(ViewName viewName) {
		return forward(Module.base, ResultPath.brand, viewName);
	}

}
