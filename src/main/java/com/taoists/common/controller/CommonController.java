package com.taoists.common.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.google.common.collect.Sets;
import com.taoists.base.service.BoxSpecService;
import com.taoists.base.service.BrandService;
import com.taoists.base.service.DataDictService;
import com.taoists.base.service.DictCategoryService;
import com.taoists.base.service.ProductService;
import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.CodeIssueService;
import com.taoists.code.service.ExcelService;
import com.taoists.code.service.FakeCodeService;
import com.taoists.common.ViewName;
import com.taoists.crm.service.CompanyService;
import com.taoists.ias.service.PurchaseBoxService;
import com.taoists.ias.service.PurchaseService;
import com.taoists.ias.service.WarehousingBoxService;
import com.taoists.ias.service.WarehousingService;
import com.taoists.sys.entity.Account;
import com.taoists.sys.service.AccountService;
import com.taoists.sys.service.MenuService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
public class CommonController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected String forward(String path, ViewName viewName) {
		return path + path + viewName.getValue();
	}

	protected String forward(Module module, String path, ViewName viewName) {
		return module.getName() + path.replaceAll("-", "") + path + viewName.getValue();
	}

	protected String redirect(String action) {
		return ViewName.redirect.getValue() + action;
	}

	protected String show = "show";
	protected String edit = "edit";
	protected String update = "update";
	protected Set<String> methods = Sets.newHashSet(show, edit, update);

	protected Long extractId(String requestURI) {
		for (String method : methods) {
			method = "/" + method + "/";
			if (StringUtils.contains(requestURI, method)) {
				String id = requestURI.substring(requestURI.indexOf(method) + method.length());
				if (id.indexOf("/") != -1) {
					id = id.substring(0, id.indexOf("/"));
				}
				if (NumberUtils.isNumber(id)) {
					return Long.valueOf(id);
				}
			}
		}
		return null;
	}

	protected Long extractId(String requestURI, String method) {
		if (StringUtils.contains(requestURI, method)) {
			String id = requestURI.substring(requestURI.indexOf(method) + method.length());
			if (id.indexOf("/") != -1) {
				id = id.substring(0, id.indexOf("/"));
			}
			if (NumberUtils.isNumber(id)) {
				return Long.valueOf(id);
			}
		}
		return null;
	}

	protected void extractParams(HttpServletRequest request) {
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String[] values = request.getParameterValues(key);
			String paramName = key.replaceAll("\\.", "_");
			if (values.length > 1) {
				Arrays.sort(values);
				request.setAttribute(paramName, values[0]);
				request.setAttribute(paramName + "_", values[1]);
				logger.debug("extractParam key[{}], value[{}]", paramName, values);
			} else {
				request.setAttribute(paramName, request.getParameter(key));
				logger.debug("extractParam key[{}], value[{}]", paramName, request.getParameter(key));
			}
		}
	}

	protected void addMethod(Collection<String> methods) {
		this.methods.addAll(methods);
	}

	protected Account getAccount(Model model) {
		Object value = model.asMap().get("currentAccount");
		if (value != null && value instanceof Account) {
			return (Account) value;
		}
		throw new IllegalStateException();
	}

	@Autowired
	protected CompanyService companyService;
	@Autowired
	protected BoxSpecService boxSpecService;
	@Autowired
	protected DataDictService dataDictService;
	@Autowired
	protected DictCategoryService dictCategoryService;
	@Autowired
	protected AccountService accountService;
	@Autowired
	protected MenuService menuService;
	@Autowired
	protected ProductService productService;
	@Autowired
	protected CodeIssueService codeIssueService;
	@Autowired
	protected BoxCodeService boxCodeService;
	@Autowired
	protected FakeCodeService fakeCodeService;
	@Autowired
	protected PurchaseService purchaseService;
	@Autowired
	protected PurchaseBoxService purchaseBoxService;
	@Autowired
	protected WarehousingService warehousingService;
	@Autowired
	protected WarehousingBoxService warehousingBoxService;
	@Autowired
	protected BrandService brandService;
	@Autowired
	protected ExcelService excelService;

}
