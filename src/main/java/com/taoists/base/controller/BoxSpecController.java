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
import com.taoists.base.entity.BoxSpec;
import com.taoists.base.entity.Product;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-1
 */
@Controller
@RequestMapping(ResultPath.boxSpec)
public class BoxSpecController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(BoxSpec boxSpec, Page page) {
		boxSpecService.findPage(boxSpec, page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		boxSpecService.findPage(page, filters);
		extractParams(request);
		return forword(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		model.addAttribute("products", productService.findAll());
		return forword(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(BoxSpec boxSpec) {
		logger.debug("create: boxSpec[{}]", boxSpec);
		boxSpecService.save(boxSpec);
		return redirect(ResultPath.boxSpec);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		model.addAttribute("products", productService.findAll());
		return forword(ViewName.edit);
	}

	@RequestMapping(value = "/update/{boxSpec.id}", method = RequestMethod.POST)
	public String update(BoxSpec boxSpec) {
		boxSpecService.evict(boxSpec.getProduct());
		logger.debug("update: boxSpec[{}]", boxSpec);
		boxSpec.setProduct(new Product(boxSpec.getProduct().getId()));
		boxSpecService.update(boxSpec);
		return redirect(ResultPath.boxSpec);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("destroy: id[{}]", id);
		boxSpecService.delete(id);
		return redirect(ResultPath.boxSpec);
	}

	@ModelAttribute("boxSpec")
	public BoxSpec getBoxSpec(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getBoxSpec: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new BoxSpec();
		}
		return boxSpecService.get(id);
	}
	
	private String forword(ViewName viewName){
		return forward(Module.base, ResultPath.boxSpec, viewName);
	}

}
