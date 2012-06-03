package com.taoists.sys.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.Cons;
import com.taoists.sys.dao.impl.MenuDaoImpl;
import com.taoists.sys.entity.Menu;
import com.taoists.sys.service.MenuService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@Transactional
@Service("menuService")
public class MenuServiceImpl extends MenuDaoImpl implements MenuService {

	@SuppressWarnings("unchecked")
	public List<Menu> loopQueryMenusByParent(List<Menu> menus) {
		if (menus != null) {
			for (Menu menu : menus) {
				if (BooleanUtils.isTrue(menu.getExpanded())) {
					menu.setChild(super.findMenusByParent(menu.getId()));
					loopQueryMenusByParent((List<Menu>) menu.getChild());
				}
			}
		}
		return menus;
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> loopQueryMenusByParent(List<Menu> menus, boolean isLeaf) {
		if (menus != null) {
			for (Menu menu : menus) {
				if (BooleanUtils.isTrue(menu.getExpanded())) {
					menu.setChild(super.findMenusByParent(menu.getId()));
					loopQueryMenusByParent((List<Menu>) menu.getChild(), isLeaf);
				}
			}
		}
		return menus;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> handle(long menuId, List<Menu> menus) {
		if (menus != null) {
			for (Menu menu : menus) {
				if (menu.getId() == menuId) {
					menu.setExpanded(!BooleanUtils.isTrue(menu.getExpanded()));
					
					if (BooleanUtils.isTrue(menu.getExpanded()) && CollectionUtils.isEmpty(menu.getChild())) {
						menu.setChild(super.findMenusByParent(menu.getId()));
						loopQueryMenusByParent((List<Menu>) menu.getChild());
					}
					return menus;
				} else {
					handle(menuId, (List<Menu>) menu.getChild());
				}
			}
		}
		return menus;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> updateMenus(List<Menu> menus, Menu menu, long id,
			String type) {
		if (menus != null) {
			for (Menu chilMenu : menus) {
				if (id == chilMenu.getId()) {
					if (Cons.delete.equals(type))
						menus.remove(chilMenu);
					else if (Cons.nonParentUpdate.equals(type))
						chilMenu.setName(menu.getName());
					else if (Cons.update.equals(type))
						((List<Menu>) chilMenu.getChild()).add(menu);
					else if (Cons.insert.equals(type))
						((List<Menu>) chilMenu.getChild()).add(menu);
					return menus;
				}
				updateMenus((List<Menu>) chilMenu.getChild(), menu, id, type);
			}
		}
		return menus;
	}

}
