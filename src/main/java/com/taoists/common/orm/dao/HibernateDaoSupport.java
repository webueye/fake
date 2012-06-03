package com.taoists.common.orm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.common.orm.PropertyFilter.MatchType;
import com.taoists.common.orm.entity.BaseEntity;
import com.taoists.common.util.ReflectionUtils;

/**
 * @author rubys
 * @since 2012-5-31
 */
public class HibernateDaoSupport<T extends BaseEntity> extends BaseDaoSupport<T> {
	
	@Override
	public List<T> findPage(Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		return findPage(detachedCriteria, page);
	}

	@Override
	public List<T> findPage(Object object, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(object));
		return findPage(detachedCriteria, page);
	}

	@Override
	public List<T> findDatas(String propertyName, Object value, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq(propertyName, value));
		return findPage(detachedCriteria, page);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findPage(DetachedCriteria detachedCriteria, Page page) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		if (page.isAutoCount()) {
			page.setTotalCount(countCriteriaResult(criteria));
		}

		setPageParameterToCriteria(criteria, page);
		page.setDatas(criteria.list());
		return (List<T>) page.getDatas();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findPage(final Page page, final Criterion... criterions) {
		Assert.notNull(page, "page is required.");
		Criteria criteria = createCriteria(criterions);
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(criteria);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(criteria, page);
		page.setDatas(criteria.list());
		return (List<T>) page.getDatas();
	}

	protected Criteria setPageParameterToCriteria(final Criteria criteria, final Page page) {
		Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");

		criteria.setFirstResult(page.getBeginIndex());
		criteria.setMaxResults(page.getPageSize());
		if (page.isOrder()) {
			if (Page.ASC.equals(page.getOrder())) {
				criteria.addOrder(Order.asc(page.getOrderBy()));
			} else {
				criteria.addOrder(Order.desc(page.getOrderBy()));
			}
		}
		return criteria;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long countCriteriaResult(final Criteria criteria) {
		CriteriaImpl impl = (CriteriaImpl) criteria;

		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries = null;

		try {
			orderEntries = (List<OrderEntry>) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("countCriteriaResult[{}]", e.getMessage());
		}

		Long totalCountObject = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = (totalCountObject != null) ? totalCountObject : 0;

		criteria.setProjection(projection);

		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			criteria.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("countCriteriaResult setFieldValue [{}]", e.getMessage());
		}
		return totalCount;
	}

	protected Criteria createCriteria(final Criterion... criterions) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		for (Criterion c : criterions) {
			detachedCriteria.add(c);
		}
		return detachedCriteria.getExecutableCriteria(getSession());
	}

	@Override
	public List<T> findPage(final Page page, final List<PropertyFilter> filters) {
		return findPage(page, buildCriterionByPropertyFilter(filters));
	}

	protected Criterion[] buildCriterionByPropertyFilter(final List<PropertyFilter> filters) {
		List<Criterion> criterions = Lists.newArrayList();
		for (PropertyFilter filter : filters) {
			if (filter.hasMultiProperties()) {
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildCriterion(param, filter.getMatchValue(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterions.add(disjunction);
			} else {
				Criterion criterion = buildCriterion(filter.getPropertyName(), filter.getMatchValue(), filter.getMatchType());
				criterions.add(criterion);
			}
		}
		return criterions.toArray(new Criterion[criterions.size()]);
	}

	protected Criterion buildCriterion(final String propertyName, final Object propertyValue, final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName is required.");
		Criterion criterion = null;
		switch (matchType) {
		case EQ:
			criterion = Restrictions.eq(propertyName, propertyValue);
			break;
		case LIKE:
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			break;
		case LE:
			criterion = Restrictions.le(propertyName, propertyValue);
			break;
		case LT:
			criterion = Restrictions.lt(propertyName, propertyValue);
			break;
		case GE:
			criterion = Restrictions.ge(propertyName, propertyValue);
			break;
		case GT:
			criterion = Restrictions.gt(propertyName, propertyValue);
		}
		return criterion;
	}

}
