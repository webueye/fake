package com.taoists.sys.service;

import java.util.List;

import com.taoists.sys.dao.MenuDao;
import com.taoists.sys.entity.Menu;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
public interface MenuService extends MenuDao {

	List<Menu> loopQueryMenusByParent(List<Menu> menus);

	List<Menu> loopQueryMenusByParent(List<Menu> menus, boolean isLeaf);

	List<Menu> handle(long menuId, List<Menu> menus);

	List<Menu> updateMenus(List<Menu> menus, Menu menu, long id, String type);

}
