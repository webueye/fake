package com.taoists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.taoists.base.entity.Product;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.service.BoxCodeService;
import com.taoists.common.bean.Page;
import com.taoists.common.util.StringUtils;
import com.taoists.ias.controller.PurchaseDetailModel;
import com.taoists.sys.entity.Menu;
import com.taoists.sys.service.MenuService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
public class MainClass {

	static Logger logger = LoggerFactory.getLogger(MainClass.class);

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");

		BoxCodeService bs = (BoxCodeService) ctx.getBean("boxCodeService");
		
		List<BoxCode> boxCodes = bs.findBoxCodes(StringUtils.stringTokenizer("467413649716"));

		Multimap<Product, BoxCode> map = HashMultimap.create();
		for (BoxCode boxCode : boxCodes) {
			map.put(boxCode.getBoxSpec().getProduct(), boxCode);
		}
		List<PurchaseDetailModel> models = Lists.newArrayList();
		Iterator<Entry<Product, Collection<BoxCode>>> it = map.asMap().entrySet().iterator();
		while(it.hasNext()){
			Entry<Product, Collection<BoxCode>> entry = it.next();
			PurchaseDetailModel model = new PurchaseDetailModel();
			model.setProduct(entry.getKey());
			model.setBoxCodes(entry.getValue());
			models.add(model);
		}
		JsonConfig conf = new JsonConfig();   
		conf.setExcludes(new String[]{"", "createDateTime", "lastModifyDateTime", });
		pl(JSONArray.fromObject(models, conf).toString());
		
	}

	@SuppressWarnings("unchecked")
	static void forEachMenus(List<Menu> list, List<Menu> menus, MenuService menuService) {
		if (CollectionUtils.isEmpty(menus)) {
			return;
		}

		for (Menu menu : menus) {
			if (menu.getLeaf()) {
				continue;
			}

			Menu m = new Menu();
			m.setId(menu.getId());
			// m.setLabel(indent(menu.getWidth()) + menu.getLabel());
			m.setWidth(menu.getWidth());
			list.add(m);
			if (menu.getChild() != null && menu.getChild().size() > 0) {
				forEachMenus(list, (List<Menu>) menu.getChild(), menuService);
			} else {
				forEachMenus(list, menuService.findMenusByParent(m.getId(), false), menuService);
			}
		}
	}

	static void pl(Object value) {
		System.out.println(value);
	}

}
