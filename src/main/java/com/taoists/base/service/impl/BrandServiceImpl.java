package com.taoists.base.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.base.entity.Brand;
import com.taoists.base.service.BrandService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-15
 */
@Service("brandService")
public class BrandServiceImpl extends HibernateDaoSupport<Brand> implements BrandService {


}
