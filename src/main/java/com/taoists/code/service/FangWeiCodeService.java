package com.taoists.code.service;

import java.util.List;

import com.taoists.code.entity.FangWeiCode;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-15
 */
public interface FangWeiCodeService extends BaseDao<FangWeiCode> {
	
	List<FangWeiCode> findCodes(String code, String codeType);

}
