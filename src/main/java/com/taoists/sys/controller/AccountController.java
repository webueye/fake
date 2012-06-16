package com.taoists.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.util.EncodeUtils;
import com.taoists.sys.controller.path.ResultPath;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@Controller
@RequestMapping(ResultPath.account)
public class AccountController extends CommonController {

	@RequestMapping
	public String list(Account account, Page page, Model model) {
		logger.debug("account[{}], page [{}]", account, page);

		accountService.findPage(account, page);
		if (account.getCompanyId() != null) {
			model.addAttribute("company", companyService.get(account.getCompanyId()));
		}
		return forward(ResultPath.account, ViewName.list);
	}

	@RequestMapping("/edit-new/{companyId}")
	public String editNew(@PathVariable Long companyId, Model model) {
		logger.debug("editNew: companyId[{}]", companyId);
		model.addAttribute("company", companyService.get(companyId));
		return forward(ResultPath.account, ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Account account, Model model) {
		logger.debug("create: account[{}]", account);

		List<Account> accounts = accountService.findDatas("username", account.getUsername());
		if (CollectionUtils.isNotEmpty(accounts)) {
			model.addAttribute("msg", "用户名已存在");
			model.addAttribute("company", companyService.get(account.getCompanyId()));
			return forward(ResultPath.account, ViewName.insert);
		}

		account.setPassword(EncodeUtils.md5(account.getPassword()));
		accountService.save(account);
		return redirect(ResultPath.account + "?companyId=" + account.getCompanyId());
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Account account, Model model) {
		logger.debug("edit: id[{}], account[{}]", id, account);

		model.addAttribute("company", companyService.get(account.getCompanyId()));
		return forward(ResultPath.account, ViewName.edit);
	}

	@RequestMapping(value = "/update/{account.id}", method = RequestMethod.POST)
	public String update(Account account) {
		logger.debug("update: account[{}]", account);

		accountService.saveOrUpdate(account);
		return redirect(ResultPath.account + "?companyId=" + account.getCompanyId());
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable long id) {
		logger.debug("show: id[{}]", id);
		return forward(ResultPath.account, ViewName.show);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("remove[{}]", id);

		accountService.delete(id);
		return redirect(ResultPath.account);
	}

	@ModelAttribute("account")
	public Account getAccount(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getAccount: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new Account();
		}
		return accountService.get(id);
	}

}
