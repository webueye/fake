package com.taoists.code.service;

import java.io.Serializable;
import java.util.List;

import com.taoists.code.entity.FakeCode;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public interface FakeCodeService extends BaseDao<FakeCode> {

	FakeCode getByPlainCode(String plainCode);

	List<String> getPlainCodeRange(Serializable codeIssueId);

	List<FakeCode> findFakeCodes(long codeIssueId, String startCode, String endCode);

}
