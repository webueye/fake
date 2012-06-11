package com.taoists.ias.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.ias.entity.PurchaseItem;
import com.taoists.ias.service.PurchaseItemService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-11
 */
@Service
public class PurchaseItemServiceImpl extends HibernateDaoSupport<PurchaseItem> implements PurchaseItemService {

}
