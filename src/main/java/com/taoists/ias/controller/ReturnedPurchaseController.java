package com.taoists.ias.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.common.util.StringUtils;
import com.taoists.crm.entity.Company;
import com.taoists.ias.controller.path.ResultPath;
import com.taoists.ias.entity.Purchase;
import com.taoists.ias.entity.PurchaseTypeEnum;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-2
 */
@Controller
@RequestMapping(ResultPath.returnedPurchase)
public class ReturnedPurchaseController extends CommonController {

	@RequestMapping
	public String list(Page page, Model model) {
		PropertyFilter filter = new PropertyFilter("EQL_purchaseCompany.id", "" + getAccount(model).getCompany().getId());
		PropertyFilter filter2 = new PropertyFilter("EQI_purchaseType", "" + PurchaseTypeEnum.returnedPurchase.getCode());
		purchaseService.findPage(page, Lists.newArrayList(filter, filter2));
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		purchaseService.findPage(page, PropertyFilter.buildFromHttpRequest(request));
		extractParams(request);
		return forward(ViewName.list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request, Purchase purchase, Model model) {
		Account account = getAccount(model);
		Company company = companyService.get(account.getCompany().getId());
		logger.debug("create: purchase[{}]", purchase);
		purchase.setCreaterId(account.getId());
		purchase.setCreater(account.getNickname());
		purchase.setSupplierCompany(company.getParent());
		purchase.setPurchaseCompany(company);

		String boxCodeValues = request.getParameter("boxCodeValues");
		purchaseService.returnedPurchase(purchase, StringUtils.stringTokenizer(boxCodeValues), account);
		return redirect(ResultPath.returnedPurchase);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		return forward(ViewName.insert);
	}

	@RequestMapping("handle-list")
	public String handleList(Page page, Model model) {
		PropertyFilter filter = new PropertyFilter("EQL_supplierCompany.id", "" + getAccount(model).getCompany().getId());
		PropertyFilter filter2 = new PropertyFilter("EQI_purchaseType", "" + PurchaseTypeEnum.returnedPurchase.getCode());
		purchaseService.findPage(page, Lists.newArrayList(filter, filter2));
		return "/ias/returnedpurchase/returned-purchase-handle-list";
	}

	@RequestMapping(value = "/handle-search", method = RequestMethod.POST)
	public String handleSearch(HttpServletRequest request, Page page, Model model) {
		purchaseService.findPage(page, PropertyFilter.buildFromHttpRequest(request));
		extractParams(request);
		return "/ias/returnedpurchase/returned-purchase-handle-list";
	}

	private String forward(ViewName viewName) {
		return forward(Module.ias, ResultPath.returnedPurchase, viewName);
	}

}
