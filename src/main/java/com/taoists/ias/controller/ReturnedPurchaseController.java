package com.taoists.ias.controller;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.base.entity.BoxSpec;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
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
	public String list(BoxSpec boxSpec, Page page) {
		purchaseService.findDatas("purchaseType", PurchaseTypeEnum.returnedPurchase.getCode(), page);
		return forward(ViewName.list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request, Purchase purchase, Model model) {
		Account account = getAccount(model);
		Company company = companyService.get(account.getCompanyId());
		Company supplierCompany = companyService.get(company.getParentId());
		logger.debug("create: purchase[{}]", purchase);
		purchase.setCreaterId(account.getId());
		purchase.setCreater(account.getNickname());
		purchase.setSupplierCompany(supplierCompany);
		purchase.setPurchaseCompany(company);
		String[] boxCodes = request.getParameterValues("boxCodes");
		purchase.setDeliveryDateTime(DateTime.now());
		purchaseService.returnedPurchase(purchase, boxCodes, account);
		return redirect(ResultPath.returnedPurchase);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		Company company = companyService.get(getAccount(model).getCompanyId());
		model.addAttribute("company", company);
		return forward(ViewName.insert);
	}

	private String forward(ViewName viewName) {
		return forward(Module.ias, ResultPath.returnedPurchase, viewName);
	}

}
