package com.taoists.common.orm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@Transactional
public abstract class BaseDaoSupport<T extends BaseEntity> implements BaseDao<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	@Override
	public void insert(Object entity) {
		getSession().save(entity);
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}
	
	@Override
	public void merge(T entity){
		getSession().merge(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public void delete(Serializable id) {
		getSession().delete(get(id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return createDetachedCriteria().getExecutableCriteria(getSession()).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findDatas(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findDatas(String propertyName, Object value) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq(propertyName, value));
		return criteria.list();
	}

	@Override
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}

	@Override
	public DetachedCriteria createDetachedCriteria(String alias) {
		return DetachedCriteria.forClass(getEntityClass(), alias);
	}

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		try {
			return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			return (Class<T>) getEntity(getClass().getSuperclass().getGenericInterfaces());
		}
	}

	protected Class<?> getEntity(Type[] types) {
		Class<?> entityClass = null;
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				entityClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
			} else if (type instanceof Class) {
				entityClass = getEntity(((Class<?>) type).getGenericInterfaces());
			}
		}
		logger.debug("ActualTypeArguments [entityClass {}]", entityClass);
		return entityClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
