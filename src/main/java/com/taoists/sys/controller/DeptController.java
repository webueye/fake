package com.taoists.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.sys.controller.path.ResultPath;
import com.taoists.sys.entity.Dept;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-21
 */
@Controller
@RequestMapping(ResultPath.dept)
public class DeptController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Page page, Model model) {
		deptService.findDatas("company.id", getAccount(model).getCompany().getId(), page);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		deptService.findPage(page, filters);
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		model.addAttribute("products", productService.findAll());
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Dept dept, String parentId) {
		if (StringUtils.isNotBlank(parentId)) {
			Dept parent = deptService.get(Long.parseLong(parentId));
			dept.setWidth(parent.getWidth()+1);
			dept.setParent(parent);
		}
		deptService.save(dept);
		return redirect(ResultPath.dept);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		model.addAttribute("products", productService.findAll());
		return forward(ViewName.edit);
	}

	@RequestMapping(value = "/update/{dept.id}", method = RequestMethod.POST)
	public String update(Dept dept, String parentId) {
		if (StringUtils.isNotBlank(parentId)) {
			Dept parent = deptService.get(Long.parseLong(parentId));
			dept.setWidth(parent.getWidth()+1);
			dept.setParent(parent);
		}
		deptService.update(dept);
		return redirect(ResultPath.dept);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("destroy: id[{}]", id);
		deptService.delete(id);
		return redirect(ResultPath.dept);
	}

	@ModelAttribute("dept")
	public Dept getDept(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new Dept();
		}
		return deptService.get(id);
	}

	@RequestMapping(value = "/nodes", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String nodes() {
		List<Dept> list = Lists.newArrayList();
		List<Dept> rootDepts = deptService.findDepts(new Dept(), true);
		forEachDepts(list, rootDepts);
		return JSONArray.fromObject(list).toString();
	}

	@SuppressWarnings("unchecked")
	private void forEachDepts(List<Dept> list, List<Dept> depts) {
		if (CollectionUtils.isEmpty(depts)) {
			return;
		}

		for (Dept dept : depts) {
			Dept d = new Dept();
			d.setId(dept.getId());
			d.setName(indent(dept.getWidth()) + dept.getName());
			d.setWidth(dept.getWidth());
			list.add(d);
			if (CollectionUtils.isNotEmpty(dept.getChild())) {
				forEachDepts(list, (List<Dept>) dept.getChild());
			} else {
				forEachDepts(list, deptService.findDeptsByParent(d.getId()));
			}
		}
	}

	private String indent(Integer width) {
		if (width == null) {
			width = 0;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < width; i++) {
			sb.append("|--");
		}
		return sb.toString();
	}

	private String forward(ViewName viewName) {
		return forward(Module.sys, ResultPath.dept, viewName);
	}

}
