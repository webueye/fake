package com.taoists.base.service;

import com.taoists.base.entity.Product;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
public interface ProductService extends BaseDao<Product> {
	
	Product getByProductNo(String productNo);

}
