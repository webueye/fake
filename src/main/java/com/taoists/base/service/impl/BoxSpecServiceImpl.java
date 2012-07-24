package com.taoists.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.taoists.base.entity.BoxSpec;
import com.taoists.base.entity.Product;
import com.taoists.base.service.BoxSpecService;
import com.taoists.base.service.ProductService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-1
 */
@Service("boxSpecService")
public class BoxSpecServiceImpl extends HibernateDaoSupport<BoxSpec> implements BoxSpecService {

	public List<BoxSpec> ifNotExistCreate(String productNo) {
		List<BoxSpec> boxSpecs = findDatas("product.productNo", productNo);
		if (boxSpecs.isEmpty()) {
			Product product = productService.getByProductNo(productNo);
			if (product == null) {
				return boxSpecs;
			}
			BoxSpec boxSpec = new BoxSpec();
			boxSpec.setProduct(product);
			save(boxSpec);
			return Lists.newArrayList(boxSpec);
		}
		return boxSpecs;
	}

	@Autowired
	private ProductService productService;

}
