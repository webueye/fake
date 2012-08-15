package com.taoists.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.base.controller.path.ResultPath;
import com.taoists.base.entity.DictCategory;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Controller
@RequestMapping(ResultPath.dictCategory)
public class DictCategoryController extends CommonController {

	@RequestMapping
	public String list(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		dictCategoryService.findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew() {
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(DictCategory dictCategory) {
		logger.debug("create: dictCategory[{}]", dictCategory);

		dictCategoryService.save(dictCategory);
		return redirect(ResultPath.dictCategory);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id) {
		logger.debug("edit: id[{}]", id);
		return forward(ViewName.edit);
	}

	@RequestMapping(value = "/update/{dictCategory.id}", method = RequestMethod.POST)
	public String update(DictCategory dictCategory) {
		logger.debug("update: dictCategory[{}]", dictCategory);

		dictCategoryService.saveOrUpdate(dictCategory);
		return redirect(ResultPath.dictCategory);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("remove: id[{}]", id);

		dictCategoryService.delete(id);
		return redirect(ResultPath.dictCategory);
	}

	@ModelAttribute("dictCategory")
	public DictCategory getDictCategory(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getDictCategory: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new DictCategory();
		}
		return dictCategoryService.get(id);
	}

	protected String forward(ViewName viewName) {
		String path = (Module.base + "/datadict" + ResultPath.dictCategory + viewName.getValue()); 
		logger.debug("forward: path[{}]", path);
		return path;
	}

}
