package com.taoists.base.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.base.entity.DictCategory;
import com.taoists.base.service.DictCategoryService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Service("dictCategoryService")
public class DictCategoryServiceImpl extends HibernateDaoSupport<DictCategory> implements DictCategoryService {

	@Override
	public DictCategory getByCode(String categoryCode){
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("categoryCode", categoryCode));
		return (DictCategory) detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
	}
	
}
