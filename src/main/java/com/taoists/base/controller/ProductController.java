package com.taoists.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.base.controller.path.ResultPath;
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
		getProductService().findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		getProductService().findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew() {
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Product product) {
		logger.debug("create: product[{}]", product);

		getProductService().save(product);
		return redirect(ResultPath.product);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id) {
		logger.debug("edit: id[{}]", id);
		return forward(ViewName.edit);
	}

	@RequestMapping(value = "/update/{dictCategory.id}", method = RequestMethod.POST)
	public String update(Product product) {
		logger.debug("update: product[{}]", product);

		getProductService().saveOrUpdate(product);
		return redirect(ResultPath.product);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("destroy: id[{}]", id);

		getDictCategoryService().delete(id);
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
		return getProductService().get(id);
	}

	private String forward(ViewName viewName) {
		String path = forward(Module.base, ResultPath.product, viewName);
		logger.debug("forward: path[{}]", path);
		return path;
	}

}
