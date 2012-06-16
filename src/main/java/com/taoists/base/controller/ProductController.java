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
import com.taoists.base.entity.DataDict;
import com.taoists.base.entity.Product;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Controller
@RequestMapping(ResultPath.product)
public class ProductController extends CommonController {

	@RequestMapping
	public String list(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		productService.findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		productService.findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		model.addAttribute("productCategories", dataDictService.findDataDictByCategoryCode("productCategory"));
		model.addAttribute("brands", brandService.findAll());
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Product product) {
		logger.debug("create: product[{}]", product);

		productService.save(product);
		return redirect(ResultPath.product);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		model.addAttribute("productCategories", dataDictService.findDataDictByCategoryCode("productCategory"));
		model.addAttribute("brands", brandService.findAll());
		return forward(ViewName.edit);
	}

	@RequestMapping(value = "/update/{product.id}", method = RequestMethod.POST)
	public String update(Product product) {
		productService.clear();
		logger.debug("update: product[{}]", product);
		product.setBrand(new Brand(product.getBrand().getId()));
		product.setProductCategory(new DataDict(product.getProductCategory().getId()));
		productService.update(product);
		return redirect(ResultPath.product);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("destroy: id[{}]", id);

		productService.delete(id);
		return redirect(ResultPath.product);
	}

	@ModelAttribute("product")
	public Product getProduct(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getProduct: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new Product();
		}
		return productService.get(id);
	}

	private String forward(ViewName viewName) {
		String path = forward(Module.base, ResultPath.product, viewName);
		logger.debug("forward: path[{}]", path);
		return path;
	}

}
