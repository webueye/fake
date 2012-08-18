package com.taoists.code.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.enums.BoxCodeStatus;
import com.taoists.code.model.BoxCodeGroup;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
public interface BoxCodeService extends BaseDao<BoxCode> {

	void batchUpdate(Collection<BoxCode> boxCodes, BoxCodeStatus status, Company storageCompany);

	List<String> queryCodes(Collection<String> boxCodes);

	List<BoxCode> queryBoxCodes(Collection<String> boxCodes);

	Map<String, String> bind(List<String> codes);

	void fromFileToBind(List<String> lines);

	BoxCode getByBoxCode(String boxCode);

	List<BoxCode> findBoxCodes(List<String> boxCodes);

	List<String> getCodeRange(Serializable codeIssueId);

	List<BoxCode> findBoxCodes(long codeIssueId, String startCode, String endCode);

	List<BoxCodeGroup> batchTrack(Page page, List<PropertyFilter> filters);

}
