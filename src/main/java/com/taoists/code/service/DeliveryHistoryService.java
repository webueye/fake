package com.taoists.code.service;

import java.util.Collection;
import java.util.List;

import com.taoists.code.model.DeliveryModel;
import com.taoists.code.model.ImpResult;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.ias.entity.Purchase;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-28
 */
public interface DeliveryHistoryService extends BaseDao<Purchase> {

	void preview(Collection<DeliveryModel> deliveryModels);

	List<ImpResult> imp(Collection<DeliveryModel> deliveryModels, Account account);

}
