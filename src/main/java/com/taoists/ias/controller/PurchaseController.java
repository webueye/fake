package com.taoists.ias.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.ias.controller.path.ResultPath;
import com.taoists.ias.entity.Purchase;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-9
 */
@Controller
@RequestMapping(ResultPath.purchase)
@SessionAttributes("currentAccount")
public class PurchaseController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Purchase purchase, Page page, @ModelAttribute("currentAccount") Account account) {
		getPurchaseService().findDatas("purchaseCompany.id", account.getCompanyId(), page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		getPurchaseService().findPage(page, PropertyFilter.buildFromHttpRequest(request));
		extractParams(request);
		return forword(ViewName.list);
	}

	private String forword(ViewName viewName) {
		return forward(Module.ias, ResultPath.purchase, viewName);
	}

}
