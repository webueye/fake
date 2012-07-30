package com.taoists.code.service.impl;

import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.BoxTrace;
import com.taoists.code.service.BoxTraceService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;
import com.taoists.ias.entity.Stock.ChangeTypeStatus;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-16
 */
@Service("boxTraceService")
public class BoxTraceServiceImpl extends HibernateDaoSupport<BoxTrace> implements BoxTraceService {

	@Override
	@Transactional
	public void save(Collection<BoxCode> boxCodes, Company company, ChangeTypeStatus eventType) {
		for (BoxCode boxCode : boxCodes) {
			BoxTrace boxTrace = new BoxTrace();
			boxTrace.setEventType(eventType);
			boxTrace.setBoxCode(boxCode);
			boxTrace.setCompany(company);
			boxTrace.setTraceDateTime(new DateTime());
			save(boxTrace);
		}
	}

}
