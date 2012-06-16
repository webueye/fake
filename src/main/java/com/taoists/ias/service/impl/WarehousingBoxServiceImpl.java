package com.taoists.ias.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;
import com.taoists.ias.entity.WarehousingBox;
import com.taoists.ias.service.WarehousingBoxService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Service
public class WarehousingBoxServiceImpl extends HibernateDaoSupport<WarehousingBox> implements WarehousingBoxService {

	@Override
	@SuppressWarnings("unchecked")
	public List<WarehousingBox> findWarehousings(String boxCode, Company company, Page page) {
		StringBuilder hql = new StringBuilder("SELECT wb FROM WarehousingBox wb WHERE ");
		StringBuilder count = new StringBuilder("SELECT count(*) FROM WarehousingBox wb WHERE ");

		StringBuilder condition = new StringBuilder();
		if(StringUtils.isNotBlank(boxCode)){
			condition.append(" wb.boxCode.boxCode like'").append(boxCode).append("%' AND ");
		}
		condition.append(" wb.warehousing.company.id=").append(company.getId()).append(" AND ");
		condition.append(" wb.boxCode.boxCode NOT IN (").append("SELECT pb.boxCode.boxCode FROM PurchaseBox pb").append(")");

		Query countQuery = getSession().createQuery(count.append(condition).toString());
		Object countResult = countQuery.uniqueResult();
		page.setTotalCount(Long.valueOf(countResult.toString()));

		Query query = getSession().createQuery(hql.append(condition).toString());
		query.setFirstResult(page.getBeginIndex());
		query.setMaxResults(page.getPageSize());
		page.setDatas(query.list());
		return (List<WarehousingBox>) page.getDatas();
	}

}
