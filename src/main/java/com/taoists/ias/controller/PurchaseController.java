package com.taoists.ias.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.taoists.base.entity.Product;
import com.taoists.code.entity.BoxCode;
import com.taoists.common.ViewName;
import com.taoists.common.bean.Page;
import com.taoists.common.controller.CommonController;
import com.taoists.common.controller.Module;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.common.util.DateUtils;
import com.taoists.common.util.StringUtils;
import com.taoists.ias.controller.path.ResultPath;
import com.taoists.ias.entity.Purchase;
import com.taoists.ias.entity.PurchaseStatus;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-9
 */
@Controller
@RequestMapping(ResultPath.purchase)
public class PurchaseController extends CommonController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Purchase purchase, Page page) {
		getPurchaseService().findPage(purchase, page);
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
	public String create(Purchase purchase, String deliveryDate) {
		logger.debug("create: purchase[{}], purchaseDate[{}]", purchase, deliveryDate);
		purchase.setDeliveryDateTime(DateUtils.toDateTime(deliveryDate));
		purchase.setStatus(PurchaseStatus.create);
		getPurchaseService().save(purchase);
		return redirect(ResultPath.purchase);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		logger.debug("edit: id[{}]", id);
		model.addAttribute("companies", getCompanyService().findAll());
		return forword(ViewName.edit);
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

	@RequestMapping(value = "/create-detail", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String createDetail(String boxCodeValues) {
		List<BoxCode> boxCodes = getBoxCodeService().findBoxCodes(StringUtils.stringTokenizer(boxCodeValues));

		Multimap<Product, BoxCode> map = HashMultimap.create();
		for (BoxCode boxCode : boxCodes) {
			map.put(boxCode.getBoxSpec().getProduct(), boxCode);
		}

		List<PurchaseDetailModel> models = Lists.newArrayList();
		Iterator<Entry<Product, Collection<BoxCode>>> it = map.asMap().entrySet().iterator();
		while (it.hasNext()) {
			Entry<Product, Collection<BoxCode>> entry = it.next();
			PurchaseDetailModel model = new PurchaseDetailModel();
			model.setProduct(entry.getKey());
			model.setBoxCodes(entry.getValue());
			models.add(model);
		}
		JsonConfig conf = new JsonConfig();
		conf.setExcludes(new String[] { "", "createDateTime", "lastModifyDateTime" });
		return JSONArray.fromObject(models, conf).toString();
	}

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
		return forward(Module.ias, ResultPath.purchase, viewName);
	}

}
