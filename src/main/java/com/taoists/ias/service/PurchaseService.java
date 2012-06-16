package com.taoists.ias.service;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.ias.entity.Purchase;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-9
 */
public interface PurchaseService extends BaseDao<Purchase> {
	
	void save(Purchase purchase, String[] boxCodes);
	
	void udpateStatus(Purchase purchase, int state, Account account);

}
