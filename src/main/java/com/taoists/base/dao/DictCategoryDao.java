package com.taoists.base.dao;

import com.taoists.base.entity.DictCategory;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
public interface DictCategoryDao extends BaseDao<DictCategory> {
	
	DictCategory getByCode(String categoryCode);

}
