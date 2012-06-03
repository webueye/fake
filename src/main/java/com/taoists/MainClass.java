package com.taoists;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.service.BoxCodeService;
import com.taoists.common.bean.Page;
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
		
		bs.findPage(new BoxCode(), new Page());
		
		bs.findPage(new BoxCode(), new Page());
		
		
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
