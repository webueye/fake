package com.taoists.base.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taoists.base.entity.Product;
import com.taoists.base.service.ProductService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Service("productService")
public class ProductServiceImpl extends HibernateDaoSupport<Product> implements ProductService {
	
	@Override
	public Product getByProductNo(String productNo){
		List<Product> products = findDatas("productNo", productNo);
		if(products.isEmpty()){
			return null;
		}
		return products.get(0);
	}

}
