package com.taoists.ias.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.common.collect.Lists;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.model.BoxModel;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.common.util.StringUtils;
import com.taoists.crm.entity.Company;
import com.taoists.ias.controller.path.ResultPath;
import com.taoists.ias.entity.Purchase;
import com.taoists.ias.entity.PurchaseBox;
import com.taoists.ias.entity.PurchaseStatus;
import com.taoists.ias.entity.PurchaseTypeEnum;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Controller
@RequestMapping(ResultPath.delivery)
@SessionAttributes("currentAccount")
public class DeliveryController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Page page, Model model) {
		PropertyFilter filter = new PropertyFilter("EQL_supplierCompany.id", "" + getAccount(model).getCompany().getId());
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

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		model.addAttribute("companies", companyService.findDatas("parent.id", getAccount(model).getCompany().getId()));
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request, Purchase purchase, String deliveryDate, @ModelAttribute("currentAccount") Account account) {
		logger.debug("create: purchase[{}], purchaseDate[{}]", purchase, deliveryDate);
		purchase.setPurchaseType(PurchaseTypeEnum.purchase.getCode());
		purchase.setCreaterId(account.getId());
		purchase.setCreater(account.getNickname());
		purchase.setSupplierCompany(new Company(account.getCompany().getId()));
		String boxCodeValues = request.getParameter("boxCodeValues");
		
		purchaseService.save(purchase, StringUtils.stringTokenizer(boxCodeValues));
		return redirect(ResultPath.delivery);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		model.addAttribute("companies", companyService.findDatas("parent.id", getAccount(model).getCompany().getId()));
		return forward(ViewName.edit);
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable long id, Model model) {
		logger.debug("show: id[{}]", id);
		List<PurchaseBox> purchaseBoxs = purchaseBoxService.findDatas("purchase.id", id);

		List<BoxCode> boxCodes = Lists.newArrayList();
		for (PurchaseBox purchaseBox : purchaseBoxs) {
			boxCodes.add(purchaseBox.getBoxCode());
		}

		model.addAttribute("boxModels", BoxModel.groupByProduct(boxCodes));
		return forward(ViewName.show);
	}

	@RequestMapping(value = "/state/{purchase.id}", method = RequestMethod.POST)
	public @ResponseBody
	String updateState(Purchase purchase, int state, @ModelAttribute("currentAccount") Account account) {
		logger.debug("send: purchase[{}]", purchase);
		purchaseService.updateStatus(purchase, state, account);
		return "";
	}

	@RequestMapping("/complete/{id}")
	public String complete(@PathVariable long id, @ModelAttribute("currentAccount") Account account) {
		logger.debug("complete: id[{}]", id);
		Purchase purchase = purchaseService.get(id);
		purchase.setStatus(PurchaseStatus.complete);
		purchase.setStatusCode(PurchaseStatus.complete.getCode());
		purchase.setCompleteDateTime(new DateTime());
		purchase.setCompleterId(account.getId());
		purchase.setCompleter(account.getNickname());
		purchaseService.saveOrUpdate(purchase);
		return redirect(ResultPath.delivery);
	}

	@ModelAttribute("purchase")
	public Purchase getPurchase(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getPurchase: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new Purchase();
		}
		return purchaseService.get(id);
	}

	private String forward(ViewName viewName) {
		return Module.ias + "/purchase/delivery-" + viewName;
	}

}
