package com.taoists.common.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoists.base.service.BoxSpecService;
import com.taoists.base.service.DataDictService;
import com.taoists.base.service.DictCategoryService;
import com.taoists.base.service.ProductService;
import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.CodeIssueService;
import com.taoists.code.service.FakeCodeService;
import com.taoists.common.ViewName;
import com.taoists.crm.service.CompanyService;
import com.taoists.ias.service.PurchaseService;
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

	protected String show = "/show/";
	protected String edit = "/edit/";
	protected String update = "/update/";
	protected String[] methods = { show, edit, update };

	protected Long extractId(String requestURI) {
		for (String method : methods) {
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
			request.setAttribute(key.replaceAll("\\.", "_"), request.getParameter(key));
		}
	}

	private CompanyService companyService;
	private BoxSpecService boxSpecService;
	private DataDictService dataDictService;
	private DictCategoryService dictCategoryService;
	private AccountService accountService;
	private MenuService menuService;
	private ProductService productService;
	private CodeIssueService codeIssueService;
	private BoxCodeService boxCodeService;
	private FakeCodeService fakeCodeService;
	private PurchaseService purchaseService;

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setFakeCodeService(FakeCodeService fakeCodeService) {
		this.fakeCodeService = fakeCodeService;
	}

	public FakeCodeService getFakeCodeService() {
		return fakeCodeService;
	}

	public void setBoxCodeService(BoxCodeService boxCodeService) {
		this.boxCodeService = boxCodeService;
	}

	public BoxCodeService getBoxCodeService() {
		return boxCodeService;
	}

	public void setCodeIssueService(CodeIssueService codeIssueService) {
		this.codeIssueService = codeIssueService;
	}

	public CodeIssueService getCodeIssueService() {
		return codeIssueService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public BoxSpecService getBoxSpecService() {
		return boxSpecService;
	}

	public void setBoxSpecService(BoxSpecService boxSpecService) {
		this.boxSpecService = boxSpecService;
	}

	public DataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(DataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public DictCategoryService getDictCategoryService() {
		return dictCategoryService;
	}

	public void setDictCategoryService(DictCategoryService dictCategoryService) {
		this.dictCategoryService = dictCategoryService;
	}

}
