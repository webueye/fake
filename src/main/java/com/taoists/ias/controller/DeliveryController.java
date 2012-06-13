package com.taoists.ias.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.common.collect.Lists;
import com.taoists.code.controller.BoxModel;
import com.taoists.code.entity.BoxCode;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.crm.entity.Company;
import com.taoists.ias.controller.path.ResultPath;
import com.taoists.ias.entity.Purchase;
import com.taoists.ias.entity.PurchaseBox;
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
	public String list(Purchase purchase, Page page, @ModelAttribute("currentAccount") Account account) {
		getPurchaseService().findDatas("supplierCompany.id", account.getCompanyId(), page);
		return forword(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page, Model model) {
		getPurchaseService().findPage(page, PropertyFilter.buildFromHttpRequest(request));
		extractParams(request);
		return forword(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		model.addAttribute("companies", getCompanyService().findAll());
		return forword(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request, Purchase purchase, String deliveryDate, @ModelAttribute("currentAccount") Account account) {
		logger.debug("create: purchase[{}], purchaseDate[{}]", purchase, deliveryDate);
		purchase.setCreaterId(account.getId());
		purchase.setCreater(account.getNickname());
		purchase.setSupplierCompany(new Company(account.getCompanyId()));

		String[] boxCodes = request.getParameterValues("boxCodes");

		// purchase.setDeliveryDateTime(DateUtils.toDateTime(deliveryDate));

		getPurchaseService().save(purchase, boxCodes);
		// return redirect(ResultPath.purchase);
		return redirect(ResultPath.purchase + "/delivery-edit-new");
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		model.addAttribute("companies", getCompanyService().findAll());
		return forword(ViewName.edit);
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable long id, Model model) {
		logger.debug("show: id[{}]", id);
		List<PurchaseBox> purchaseBoxs = getPurchaseBoxService().findDatas("purchase.id", id);

		List<BoxCode> boxCodes = Lists.newArrayList();
		for (PurchaseBox purchaseBox : purchaseBoxs) {
			boxCodes.add(purchaseBox.getBoxCode());
		}

		model.addAttribute("boxModels", BoxModel.groupByProduct(boxCodes));
		return forword(ViewName.show);
	}

	@RequestMapping(value = "/update/{purchase.id}", method = RequestMethod.POST)
	public String update(Purchase purchase) {
		logger.debug("update: boxSpec[{}]", purchase);
		getPurchaseService().saveOrUpdate(purchase);
		return redirect(ResultPath.purchase);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("destroy: id[{}]", id);
		getBoxSpecService().delete(id);
		return redirect(ResultPath.purchase);
	}

//	@RequestMapping(value = "/create-detail", produces = "text/plain;charset=UTF-8")
//	public @ResponseBody
//	String createDetail(String boxCodeValues) {
//		List<BoxCode> boxCodes = getBoxCodeService().findBoxCodes(StringUtils.stringTokenizer(boxCodeValues));
//
//		JsonConfig conf = new JsonConfig();
//		conf.setExcludes(new String[] { "", "createDateTime", "lastModifyDateTime" });
//		return JSONArray.fromObject(BoxModel.groupByProduct(boxCodes), conf).toString();
//	}

	@ModelAttribute("purchase")
	public Purchase getPurchase(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		logger.debug("getPurchase: request.getRequestURI[{}], id[{}]", requestURI, id);

		if (id == null) {
			return new Purchase();
		}
		return getPurchaseService().get(id);
	}

	private String forword(ViewName viewName) {
		return Module.ias + "/purchase/delivery-" + viewName;
	}

}