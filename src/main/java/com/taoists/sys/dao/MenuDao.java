package com.taoists.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.entity.Menu;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
public interface MenuDao extends BaseDao<Menu> {

	List<Menu> getRootMenus();

	List<Menu> findMenus(Menu menu, boolean parentIsNull);

	List<Menu> findMenusByParent(Serializable parentId);

	List<Menu> findMenusByParent(Serializable parentId, boolean isLeaf);

}
