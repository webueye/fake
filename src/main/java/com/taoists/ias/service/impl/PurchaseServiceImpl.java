package com.taoists.ias.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.ias.entity.Purchase;
import com.taoists.ias.service.PurchaseService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-9
 */
@Service("purchaseService")
public class PurchaseServiceImpl extends HibernateDaoSupport<Purchase> implements PurchaseService {

}
