package com.taoists.base.service;

import java.util.List;

import com.taoists.base.entity.BoxSpec;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-1
 */
public interface BoxSpecService extends BaseDao<BoxSpec> {
	
	List<BoxSpec> ifNotExistCreate(String productNo, int actualNum);

}
