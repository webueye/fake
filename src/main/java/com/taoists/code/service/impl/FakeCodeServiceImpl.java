package com.taoists.code.service.impl;

import org.hibernate.criterion.DetachedCriteria;
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

}
