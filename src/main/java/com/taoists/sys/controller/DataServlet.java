package com.taoists.sys.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.taoists.base.entity.DataDict;
import com.taoists.base.service.DataDictService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-30
 */
@SuppressWarnings("serial")
public class DataServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());

		webApplicationContext.getBean("dictCategoryService");
		DataDictService dataDictService = (DataDictService) webApplicationContext.getBean("dataDictService");
		List<DataDict> dataDicts = dataDictService.findAll();

		Multimap<String, DataDict> group = HashMultimap.create();
		for (DataDict dataDict : dataDicts) {
			group.put(dataDict.getDictCategory().getCategoryCode(), dataDict);
		}

		for (Entry<String, Collection<DataDict>> entry : group.asMap().entrySet()) {
			config.getServletContext().setAttribute(entry.getKey() + "s", entry.getValue());
		}
	}

}
