package com.taoists.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taoists.common.controller.CommonController;
import com.taoists.common.util.EncodeUtils;
import com.taoists.sys.controller.path.ResultPath;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-16
 */
@Controller
@SessionAttributes("currentAccount")
@RequestMapping(ResultPath.user)
public class UserController extends CommonController {

	@RequestMapping("/edit-new")
	public String editNew() {
		return "/sys/user/password";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(String oldPassword, String newPassword, String confirmPassword, Model model) {
		Account account = accountService.get(getAccount(model).getId());
		if (!account.getPassword().equals(EncodeUtils.md5(oldPassword))) {
			model.addAttribute("msg", "原始密码不正确");
		} else if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("msg", "两次输入的密码不相同");
		} else {
			account.setPassword(EncodeUtils.md5(newPassword));
			accountService.update(account);
			model.addAttribute("msg", "修改成功");
		}
		return "/sys/user/password";
	}

}
