package com.taoists.ias.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.common.ViewName;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.crm.entity.Company;
import com.taoists.ias.controller.path.ResultPath;
import com.taoists.ias.entity.Purchase;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Controller
@RequestMapping(ResultPath.warehousing)
public class WarehousingController extends CommonController {

	@RequestMapping
	public String list() {

		return forword(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew() {
		return forword(ViewName.insert);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request, @ModelAttribute("currentAccount") Account account) {

		String[] boxCodes = request.getParameterValues("boxCodes");

		return redirect(ResultPath.purchase + "/delivery-edit-new");
	}

	private String forword(ViewName viewName) {
		return forward(Module.ias, ResultPath.warehousing, viewName);
	}

}
