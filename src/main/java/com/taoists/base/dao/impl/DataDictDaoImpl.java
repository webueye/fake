package com.taoists.base.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.base.dao.DataDictDao;
import com.taoists.base.entity.DataDict;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Transactional
@Repository("dataDictDao")
public class DataDictDaoImpl extends HibernateDaoSupport<DataDict> implements DataDictDao {

	public List<DataDict> findDataDictByCategoryCode(String categoryCode) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.categoryCode", categoryCode));
		return findDatas(detachedCriteria);
	}

	public List<DataDict> findDataDictByCategoryCode(String categoryCode, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.categoryCode", categoryCode));
		return findPage(detachedCriteria, page);
	}

}
