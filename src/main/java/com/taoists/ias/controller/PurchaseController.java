package com.taoists.ias.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.common.collect.Lists;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.ias.controller.path.ResultPath;
import com.taoists.ias.entity.PurchaseTypeEnum;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-9
 */
@Controller
@RequestMapping(ResultPath.purchase)
@SessionAttributes("currentAccount")
public class PurchaseController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Page page, Model model) {
		PropertyFilter filter = new PropertyFilter("EQL_purchaseCompany.id", "" + getAccount(model).getCompany().getId());
		PropertyFilter filter2 = new PropertyFilter("EQI_purchaseType", "" + PurchaseTypeEnum.purchase.getCode());
		purchaseService.findPage(page, Lists.newArrayList(filter, filter2));
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		purchaseService.findPage(page, PropertyFilter.buildFromHttpRequest(request));
		extractParams(request);
		return forward(ViewName.list);
	}

	private String forward(ViewName viewName) {
		return forward(Module.ias, ResultPath.purchase, viewName);
	}

}
