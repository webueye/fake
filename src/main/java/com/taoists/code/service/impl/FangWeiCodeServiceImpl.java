package com.taoists.code.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.code.entity.FangWeiCode;
import com.taoists.code.service.FangWeiCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-15
 */
@Service
public class FangWeiCodeServiceImpl extends HibernateDaoSupport<FangWeiCode> implements FangWeiCodeService {

	@Override
	@SuppressWarnings("unchecked")
	public List<FangWeiCode> findCodes(String code, String codeType) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("codeNo", code));
		detachedCriteria.add(Restrictions.eq("codeType", codeType));
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}

}
