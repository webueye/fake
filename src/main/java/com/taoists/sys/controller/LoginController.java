package com.taoists.sys.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taoists.common.controller.CommonController;
import com.taoists.sys.controller.path.ResultPath;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@Controller
@RequestMapping(ResultPath.login)
public class LoginController extends CommonController {

	@RequestMapping
	public String index() {
		return redirect("/login.jsp");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpSession session, Account account, RedirectAttributes redirectAttributes) {
		logger.debug("login: account[{}]", account);
		
		List<Account> accounts = getAccountService().findDatas("username", account.getUsername());
		if (CollectionUtils.isEmpty(accounts)) {
			redirectAttributes.addFlashAttribute("msg", "notExist");
			return redirect("/login");
		}
		Account acc = accounts.get(0);
		if (!acc.getPassword().equals(account.getPassword())) {
			redirectAttributes.addFlashAttribute("msg", "passwordNotCorrect");
			return redirect("/login");
		}
		session.setAttribute("currentAccount", acc);
		return redirect("/main/index.jsp");
	}

}
