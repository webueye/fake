package com.taoists.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.taoists.common.Cons;
import com.taoists.common.ViewName;
import com.taoists.common.controller.CommonController;
import com.taoists.sys.controller.path.ResultPath;
import com.taoists.sys.entity.Account;
import com.taoists.sys.entity.Menu;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends CommonController {

	@RequestMapping
	public String list(HttpSession session) {
		List<Menu> menus = menuService.getRootMenus();
		menuService.loopQueryMenusByParent(menus);
		session.setAttribute("menus", menuService.loopQueryMenusByParent(menus));
		return forward(ResultPath.menu, ViewName.list);
	}

	@RequestMapping("/left")
	public String left(HttpSession session, Model model) {
		List<Menu> menus = menuService.getRootMenus();
		menuService.loopQueryMenusByParent(menus);

		Account account = getAccount(model);
		List<Menu> expunges = Lists.newArrayList();
		if (BooleanUtils.isNotTrue(account.getAdmin())) {
			if (CollectionUtils.isNotEmpty(account.getRoles())) {
				for (Menu menu : menus) {
					for (Long id : account.getRoles()) {
						if (menu.getId().longValue() != id.longValue()) {
							expunges.add(menu);
						}
					}
				}
			} else {
				menus = Lists.newArrayList();
			}
			menus.removeAll(expunges);
		}
		session.setAttribute("menus", menus);
		return redirect("/main/left.jsp");
	}

	@RequestMapping("/edit-new")
	public String editNew() {
		return forward(ResultPath.menu, ViewName.insert);
	}

	@RequestMapping("/new/{parentId}")
	public String editNew(Menu menu, @PathVariable String parentId, Model model) {
		logger.debug("menu[{}], parentId[{}]", menu, parentId);
		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = new Menu();
			parent.setId(Long.parseLong(parentId));
			menu.setParent(parent);
			model.addAttribute("menu", menu);
		}
		return forward(ResultPath.menu, ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Menu menu, String parentId) {
		logger.debug("create[{}]", menu);

		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = menuService.get(Long.parseLong(parentId));
			menu.setWidth(parent.getWidth() + 1);
			menu.setParent(parent);
		}
		menuService.save(menu);
		return redirect(ResultPath.menu);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Menu menu, String parentId) {
		logger.debug("create[{}]", menu);

		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = menuService.get(Long.parseLong(parentId));
			menu.setWidth(parent.getWidth() + 1);
			menu.setParent(parent);
		}
		menuService.merge(menu);
		return redirect(ResultPath.menu);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("{id}/expand")
	public String expand(@PathVariable long id, HttpSession session) {
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.handle(id, menus);
		session.setAttribute("menus", menus);
		return forward(ResultPath.menu, ViewName.list);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("{id}/menu")
	public String menu(@PathVariable long id, HttpSession session) {
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.handle(id, menus);
		session.setAttribute("menus", menus);
		return redirect("/main/left.jsp");
	}

	@RequestMapping("{id}")
	public String show(@PathVariable long id, Model model) {
		logger.debug("show[{}]", id);

		model.addAttribute("menu", menuService.get(id));
		return forward(ResultPath.menu, ViewName.edit);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/destroy/{id}")
	public String remove(@PathVariable long id, HttpSession session) {
		logger.debug("remove[{}]", id);
		menuService.delete(id);
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.updateMenus(menus, new Menu(), id, Cons.delete);
		return redirect(ResultPath.menu);
	}

	@RequestMapping(value = "/nodes", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String nodes() {
		List<Menu> list = new ArrayList<Menu>();
		Menu rootMenu = new Menu();
		rootMenu.setLeaf(false);

		List<Menu> rootMenus = menuService.findMenus(rootMenu, true);
		forEachMenus(list, rootMenus);
		return JSONArray.fromObject(list).toString();
	}

	@SuppressWarnings("unchecked")
	private void forEachMenus(List<Menu> list, List<Menu> menus) {
		if (CollectionUtils.isEmpty(menus)) {
			return;
		}

		for (Menu menu : menus) {
			if (menu.getLeaf()) {
				continue;
			}

			Menu m = new Menu();
			m.setId(menu.getId());
			m.setLabel(indent(menu.getWidth()) + menu.getLabel());
			m.setWidth(menu.getWidth());
			list.add(m);
			if (CollectionUtils.isNotEmpty(menu.getChild())) {
				forEachMenus(list, (List<Menu>) menu.getChild());
			} else {
				forEachMenus(list, menuService.findMenusByParent(m.getId(), false));
			}
		}
	}

	private String indent(Integer width) {
		if (width == null) {
			width = 0;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < width; i++) {
			sb.append("|--");
		}
		return sb.toString();
	}

}
