package com.taoists.ias.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.code.model.BoxModel;
import com.taoists.code.service.BoxTraceService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;
import com.taoists.ias.entity.Stock;
import com.taoists.ias.entity.Stock.ChangeTypeStatus;
import com.taoists.ias.entity.Warehousing;
import com.taoists.ias.service.ProductInventoryService;
import com.taoists.ias.service.StockService;
import com.taoists.ias.service.WarehousingService;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Service
public class StockServiceImpl extends HibernateDaoSupport<Stock> implements StockService {

	@Override
	@Transactional
	public void inStock(List<BoxModel> boxModels, Account account) {
		Warehousing warehousing = new Warehousing();
		warehousing.setCompany(new Company(account.getCompanyId()));
		warehousing.setOperator(account);
		warehousing.setWarehousingDateTime(new DateTime());

		warehousingService.save(warehousing, boxModels);
		saveStock(boxModels, new Company(account.getCompanyId()), ChangeTypeStatus.in);
	}

	@Override
	@Transactional
	public void outStock(List<BoxModel> boxModels, Account account) {
		saveStock(boxModels, new Company(account.getCompanyId()), ChangeTypeStatus.out);
	}

	@Override
	@Transactional
	public void saveStock(List<BoxModel> boxModels, Company company, ChangeTypeStatus status) {
		for (BoxModel boxModel : boxModels) {
			Stock stock = new Stock();
			stock.setChangeType(status);
			stock.setCompany(company);
			stock.setProduct(boxModel.getProduct());
			if (ChangeTypeStatus.in.getCode() == status.getCode()) {
				stock.setInCount(boxModel.getTotalCount());
				stock.setInAmount(boxModel.getProduct().getMarketPrice().multiply(new BigDecimal(boxModel.getBoxCount())));
				productInventoryService.inStock(boxModel, company);
			} else {
				stock.setOutCount(boxModel.getTotalCount());
				stock.setOutAmount(boxModel.getProduct().getMarketPrice().multiply(new BigDecimal(boxModel.getBoxCount())));
				productInventoryService.outStock(boxModel, company);
			}
			save(stock);

			boxTraceService.save(boxModel.getBoxCodes(), company, status);
		}
	}

	@Autowired
	private BoxTraceService boxTraceService;
	@Autowired
	private WarehousingService warehousingService;
	@Autowired
	private ProductInventoryService productInventoryService;

}
