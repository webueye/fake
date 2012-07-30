package com.taoists.ias.service;

import java.util.List;

import com.taoists.code.model.BoxModel;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.crm.entity.Company;
import com.taoists.ias.entity.Stock;
import com.taoists.ias.entity.Stock.ChangeTypeStatus;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
public interface StockService extends BaseDao<Stock> {

	void inStock(List<BoxModel> boxModels, Account account);

	void outStock(List<BoxModel> boxModels, Account account);
	
	void saveStock(List<BoxModel> boxModels, Company company, ChangeTypeStatus status);

}
