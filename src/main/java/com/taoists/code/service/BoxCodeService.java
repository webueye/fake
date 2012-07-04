package com.taoists.code.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.BoxCodeStatus;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
public interface BoxCodeService extends BaseDao<BoxCode> {
	
	void batchUpdate(Collection<BoxCode> boxCodes, BoxCodeStatus status, Company storageCompany);

	Map<String, String> bind(List<String> codes);

	BoxCode getByBoxCode(String boxCode);

	List<BoxCode> findBoxCodes(List<String> boxCodes);

}
