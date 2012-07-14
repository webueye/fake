package com.taoists.code.service;

import com.taoists.code.entity.FakeCode;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public interface FakeCodeService extends BaseDao<FakeCode> {

	FakeCode getByPlainCode(String plainCode);

}
