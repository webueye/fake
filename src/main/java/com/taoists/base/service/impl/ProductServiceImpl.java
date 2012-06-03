package com.taoists.base.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.base.dao.impl.ProductDaoImpl;
import com.taoists.base.service.ProductService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Service("productService")
public class ProductServiceImpl extends ProductDaoImpl implements ProductService {

}
