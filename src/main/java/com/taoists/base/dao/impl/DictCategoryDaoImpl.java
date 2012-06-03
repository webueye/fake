package com.taoists.base.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.base.dao.DictCategoryDao;
import com.taoists.base.entity.DictCategory;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Transactional
@Repository("dictCategoryDao")
public class DictCategoryDaoImpl extends HibernateDaoSupport<DictCategory> implements DictCategoryDao {
	
	public DictCategory getByCode(String categoryCode){
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("categoryCode", categoryCode));
		return (DictCategory) detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
	}
	
}
