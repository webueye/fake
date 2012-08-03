package com.taoists.ias.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taoists.common.ViewName;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.util.StringUtils;
import com.taoists.ias.controller.path.ResultPath;
import com.taoists.ias.entity.Warehousing;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Controller
@RequestMapping(ResultPath.warehousing)
public class WarehousingController extends CommonController {

	@RequestMapping("/edit-new")
	public String editNew() {
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request, Warehousing warehousing, RedirectAttributes redirectAttributes) {
		String boxCodeValues = request.getParameter("boxCodeValues");
		warehousingService.saveByBoxCode(warehousing, StringUtils.stringTokenizer(boxCodeValues));
		redirectAttributes.addAttribute("msg", "success");
		return redirect(ResultPath.warehousing + "/edit-new");
	}

	private String forward(ViewName viewName) {
		return forward(Module.ias, ResultPath.warehousing, viewName);
	}

}
