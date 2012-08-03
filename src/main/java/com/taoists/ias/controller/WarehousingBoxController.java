package com.taoists.ias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.crm.entity.Company;
import com.taoists.ias.controller.path.ResultPath;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Controller
@SessionAttributes("currentAccount")
@RequestMapping(ResultPath.warehousingBox)
public class WarehousingBoxController extends CommonController {

	@RequestMapping
	public String list(Model model, Page page) {
		warehousingBoxService.findWarehousings(null, new Company(getAccount(model).getCompany().getId()), page);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(String boxCode, Model model, Page page) {
		warehousingBoxService.findWarehousings(boxCode, new Company(getAccount(model).getCompany().getId()), page);
		model.addAttribute("boxCode", boxCode);
		return forward(ViewName.list);
	}

	private String forward(ViewName viewName) {
		return "/ias/warehousing/warehousing-box" + viewName.getValue();
	}

}
