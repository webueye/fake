package com.taoists.ias.service;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.ias.entity.Purchase;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-9
 */
public interface PurchaseService extends BaseDao<Purchase> {

	Purchase getByPurchaseNo(String purchaseNo);

	void save(Purchase purchase, String[] boxCodes);

	void returnedPurchase(Purchase purchase, String[] boxCodeValues, Account account);

	void updateStatus(Purchase purchase, int state, Account account);

}
