package com.taoists.code.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.BoxCodeStatus;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.FakeCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Transactional
@Service("boxCodeService")
public class BoxCodeServiceImpl extends HibernateDaoSupport<BoxCode> implements BoxCodeService {
	
	@Override
	@Transactional
	public void batchUpdate(Collection<BoxCode> boxCodes, BoxCodeStatus status, Company storageCompany) {
		Assert.notNull(boxCodes, "boxCodes is required.");
		Assert.notNull(status, "status is required.");

		for (BoxCode boxCode : boxCodes) {
			boxCode.setStatus(status);
			boxCode.setStatusCode(status.getCode());
			update(boxCode);
		}
	}

	@Override
	@Transactional
	public Map<String, String> bind(List<String> codes) {
		logger.debug("codes[{}], size[{}]", codes, codes.size());

		Map<String, String> map = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(codes)) {
			String code = codes.get(codes.size() - 1);
			BoxCode boxCode = getByBoxCode(code);
			if (boxCode == null) {
				map.put("boxCodeNotExist", code);
				return map;
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < codes.size() - 1; i++) {
				FakeCode fakeCode = fakeCodeService.getByPlainCode(codes.get(i));
				if (fakeCode == null) {
					sb.append(codes.get(i)).append("\n");
					continue;
				}
				fakeCode.setBoxCode(boxCode);
				fakeCodeService.update(fakeCode);
			}
			if (StringUtils.isNotBlank(sb.toString())) {
				map.put("fakeCodeNotExist", sb.toString());
			}
		}
		return map;
	}

	@Override
	public BoxCode getByBoxCode(String boxCode) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("boxCode", boxCode));
		return (BoxCode) detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BoxCode> findBoxCodes(List<String> boxCodes) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.in("boxCode", boxCodes));
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}

	@Autowired
	private FakeCodeService fakeCodeService;

}
