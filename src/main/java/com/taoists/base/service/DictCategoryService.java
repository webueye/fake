package com.taoists.base.service;

import com.taoists.base.entity.DictCategory;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
public interface DictCategoryService extends BaseDao<DictCategory> {

	DictCategory getByCode(String categoryCode);

}
