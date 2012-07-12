package com.taoists.code.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.code.entity.FakeCode;
import com.taoists.code.service.FakeCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@Service("fakeCodeService")
public class FakeCodeServiceImpl extends HibernateDaoSupport<FakeCode> implements FakeCodeService {

	@Override
	public FakeCode getByPlainCode(String plainCode) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("plainCode", plainCode));
		return (FakeCode) detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getPlainCodeRange(Serializable codeIssueId) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("codeIssue.id", codeIssueId));
		criteria.setProjection(Projections.projectionList().add(Projections.min("plainCode")).add(Projections.max("plainCode")));
		return criteria.getExecutableCriteria(getSession()).list();

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FakeCode> findFakeCodes(long codeIssueId, String startCode, String endCode) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("codeIssue.id", codeIssueId));
		criteria.add(Restrictions.between("plainCode", startCode, endCode));
		return criteria.getExecutableCriteria(getSession()).list();
	}

}
