package com.taoists.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.base.controller.path.ResultPath;
import com.taoists.base.entity.DataDict;
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
@RequestMapping(ResultPath.dataDict)
public class DataDictController extends CommonController {

	@RequestMapping("/list/{categoryId}")
	public String list(@PathVariable Long categoryId, Page page, Model model) {
		logger.debug("list: dictCategory.id[{}], page[{}]", categoryId, page);

		DictCategory dictCategory = dictCategoryService.get(categoryId);

		DataDict dataDict = new DataDict();
		dataDict.setDictCategory(dictCategory);
		dataDictService.findDatas("dictCategory.id", categoryId, page);
		model.addAttribute("dictCategory", dictCategory);
		return forword(ViewName.list);
	}
	
	@RequestMapping("/category/{categoryCode}")
	public String category(@PathVariable String categoryCode, Page page, Model model) {
		logger.debug("list: dictCategory.categoryCode[{}], page[{}]", categoryCode, page);
		
		DictCategory dictCategory = dictCategoryService.getByCode(categoryCode);
		
		DataDict dataDict = new DataDict();
		dataDict.setDictCategory(dictCategory);
		dataDictService.findDataDictByCategoryCode(categoryCode, page);
		model.addAttribute("dictCategory", dictCategory);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(HttpServletRequest request, DataDict dataDict, Page page) {
		logger.debug("list: dataDict[{}], page[{}]", dataDict, page);
		dataDictService.findPage(page, PropertyFilter.buildFromHttpRequest(request));
		return forword(ViewName.list);
	}

	@RequestMapping("/edit-new/{categoryId}")
	public String editNew(@PathVariable Long categoryId, Model model) {
		logger.debug("editNew: categoryId[{}]", categoryId);
		model.addAttribute("dictCategory", dictCategoryService.get(categoryId));
		return forword(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(DataDict dataDict) {
		logger.debug("create: dataDict[{}]", dataDict);

		dataDictService.save(dataDict);
		return redirect("/data-dict/list/" + dataDict.getDictCategory().getId());
	}

	@RequestMapping(value = "/update/{dataDict.id}", method = RequestMethod.POST)
	public String update(DataDict dataDict) {
		logger.debug("dataDict[{}]", dataDict);
		dataDictService.saveOrUpdate(dataDict);
		return redirect("/data-dict/list/" + dataDict.getDictCategory().getId());
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id) {
		logger.debug("edit: dataDict.id[{}]", id);
		return forword(ViewName.edit);
	}

	@RequestMapping("/destroy/{categoryId}/{id}")
	public String destroy(@PathVariable Long categoryId, @PathVariable Long id) {
		dataDictService.delete(id);
		return redirect("/data-dict/list/" + categoryId);
	}

	@ModelAttribute("dataDict")
	public DataDict getDataDict(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getDataDict: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new DataDict();
		}
		return dataDictService.get(id);
	}
	
	private String forword(ViewName viewName){
		return forward(Module.base, ResultPath.dataDict, viewName);
	}

}
