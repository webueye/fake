package com.taoists.ias.service;

import com.taoists.code.model.BoxModel;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.crm.entity.Company;
import com.taoists.ias.entity.ProductInventory;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-2
 */
public interface ProductInventoryService extends BaseDao<ProductInventory> {

	void inStock(BoxModel boxModel, Company company);

	void outStock(BoxModel boxModel, Company company);

}
