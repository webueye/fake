package com.taoists.sys.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.dao.MenuDao;
import com.taoists.sys.entity.Menu;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@Transactional
@Repository("menuDao")
public class MenuDaoImpl extends HibernateDaoSupport<Menu> implements MenuDao {

	public List<Menu> getRootMenus() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.isNull("parent"));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		return findDatas(detachedCriteria);
	}

	public List<Menu> findMenus(Menu menu, boolean parentIsNull) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(menu));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		if (parentIsNull) {
			detachedCriteria.add(Restrictions.isNull("parent"));
		}
		return findDatas(detachedCriteria);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findMenusByParent(Serializable parentId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("parent.id", parentId));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findMenusByParent(Serializable parentId, boolean isLeaf) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("parent.id", parentId));
		criteria.add(Restrictions.eq("leaf", isLeaf));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		return criteria.list();
	}

}
