package com.taoists.ias.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.taoists.base.entity.Product;
import com.taoists.code.model.BoxModel;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;
import com.taoists.exception.InventoryNotEnoughExceptioin;
import com.taoists.ias.entity.ProductInventory;
import com.taoists.ias.service.ProductInventoryService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-2
 */
@Service("productInventoryService")
public class ProductInventoryServiceImpl extends HibernateDaoSupport<ProductInventory> implements ProductInventoryService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional
	public synchronized void save(ProductInventory entity) {
		Assert.notNull(entity.getCompany());
		Assert.notNull(entity.getProduct());
		ProductInventory productInventory = getProductInventory(entity.getProduct(), entity.getCompany());
		if (productInventory == null) {
			super.save(entity);
		} else {
			logger.error("Product[{}], company[{}] is existed in productInventory", entity.getProduct(), entity.getCompany());
			throw new IllegalStateException();
		}
	}

	@Override
	@Transactional
	public synchronized void update(ProductInventory entity) {
		Assert.notNull(entity.getId());
		Assert.notNull(entity.getCompany());
		Assert.notNull(entity.getProduct());
		ProductInventory productInventory = getProductInventory(entity.getProduct(), entity.getCompany());
		if (productInventory == null) {
			logger.error("Product[{}], company[{}] is not existed in productInventory", entity.getProduct(), entity.getCompany());
			throw new IllegalStateException();
		} else if (entity.getId().longValue() != productInventory.getId().longValue()) {
			logger.error("Product[{}] and company[{}] of productInventory is not match", entity.getProduct(), entity.getCompany());
			throw new IllegalStateException();
		} else {
			super.update(entity);
		}
	}

	@Override
	@Deprecated
	public synchronized void saveOrUpdate(ProductInventory entity) {
		throw new IllegalAccessError();
	}

	public synchronized void inStock(BoxModel boxModel, Company company) {
		ProductInventory productInventory = getProductInventory(boxModel.getProduct(), company);
		if (productInventory == null) {
			productInventory = new ProductInventory();
			productInventory.setProduct(boxModel.getProduct());
			productInventory.setCompany(company);
			productInventory.setAvailableQty(boxModel.getTotalCount());
			save(productInventory);
		} else {
			productInventory.setProduct(boxModel.getProduct());
			productInventory.setCompany(company);
			productInventory.setAvailableQty(boxModel.getTotalCount() + productInventory.getAvailableQty());
			update(productInventory);
		}
	}

	@Override
	public synchronized void outStock(BoxModel boxModel, Company company) {
		ProductInventory productInventory = getProductInventory(boxModel.getProduct(), company);
		if (productInventory == null) {
			throw new InventoryNotEnoughExceptioin();
		} else {
			if (productInventory.getAvailableQty() - boxModel.getTotalCount() < 0) {
				throw new InventoryNotEnoughExceptioin();
			}
			productInventory.setAvailableQty(productInventory.getAvailableQty() - boxModel.getTotalCount());
			update(productInventory);
		}
	}

	@SuppressWarnings("unchecked")
	public ProductInventory getProductInventory(Product product, Company company) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("product.id", product.getId()));
		detachedCriteria.add(Restrictions.eq("company.id", company.getId()));
		List<ProductInventory> productInventories = detachedCriteria.getExecutableCriteria(getSession()).list();
		if (CollectionUtils.isNotEmpty(productInventories)) {
			return productInventories.get(0);
		}
		return null;
	}

}
