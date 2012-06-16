package com.taoists.code.service;

import java.util.Collection;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.BoxTrace;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-16
 */
public interface BoxTraceService extends BaseDao<BoxTrace> {

	void save(Collection<BoxCode> boxCodes, Company company);

}
