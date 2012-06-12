package com.taoists.ias.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.ias.entity.Stock;
import com.taoists.ias.service.StockService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Service
public class StockServiceImpl extends HibernateDaoSupport<Stock> implements StockService {

}
