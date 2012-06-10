package com.taoists.base.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.base.entity.DataDict;
import com.taoists.base.service.DataDictService;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@Service("dataDictService")
public class DataDictServiceImpl extends HibernateDaoSupport<DataDict> implements DataDictService {

	@Override
	public List<DataDict> findDataDictByCategoryCode(String categoryCode) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.categoryCode", categoryCode));
		return findDatas(detachedCriteria);
	}

	@Override
	public List<DataDict> findDataDictByCategoryCode(String categoryCode, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.createAlias("dictCategory", "dc");
		detachedCriteria.add(Restrictions.eq("dc.categoryCode", categoryCode));
		return findPage(detachedCriteria, page);
	}

}
