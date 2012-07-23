package com.taoists.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.entity.Dept;
import com.taoists.sys.service.DeptService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-21
 */
@Service
public class DeptServiceImpl extends HibernateDaoSupport<Dept> implements DeptService {

	@Override
	public List<Dept> findDepts(Dept dept, boolean parentIsNull) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(dept));
		if (parentIsNull) {
			detachedCriteria.add(Restrictions.isNull("parent"));
		}
		return findDatas(detachedCriteria);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Dept> findDeptsByParent(Serializable parentId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("parent.id", parentId));
		return criteria.list();
	}

}
