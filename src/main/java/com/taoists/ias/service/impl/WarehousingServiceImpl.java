package com.taoists.ias.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taoists.code.controller.BoxModel;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.service.BoxCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.ias.entity.Stock;
import com.taoists.ias.entity.Warehousing;
import com.taoists.ias.entity.WarehousingBox;
import com.taoists.ias.entity.WarehousingItem;
import com.taoists.ias.service.StockService;
import com.taoists.ias.service.WarehousingBoxService;
import com.taoists.ias.service.WarehousingItemService;
import com.taoists.ias.service.WarehousingService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
@Service
public class WarehousingServiceImpl extends HibernateDaoSupport<Warehousing> implements WarehousingService {

	@Override
	public void save(Warehousing warehousing, String[] boxCodeValues) {
		save(warehousing);

		List<BoxCode> boxCodes = boxCodeService.findBoxCodes(Arrays.asList(boxCodeValues));
		List<BoxModel> models = BoxModel.groupByProduct(boxCodes);

		for (BoxModel model : models) {
			WarehousingItem item = new WarehousingItem();
			item.setWarehousing(warehousing);
			item.setMemo(warehousing.getMemo());
			item.setProduct(model.getProduct());
			item.setPrice(model.getProduct().getMarketPrice());
			item.setQty(model.getBoxCount());
			item.setSubAmount(model.getProduct().getMarketPrice().multiply(new BigDecimal(model.getBoxCount())));
			warehousingItemService.save(item);
			
			for (BoxCode boxCode : model.getBoxCodes()) {
				WarehousingBox box = new WarehousingBox();
				box.setBoxCode(boxCode);
				box.setWarehousing(warehousing);
				warehousingBoxService.save(box);
			}
			
			Stock stock = new Stock();
			stock.setCompany(warehousing.getCompany());
			stock.setProduct(model.getProduct());
			stock.setInCount(item.getQty());
			stock.setInAmount(item.getSubAmount());
			stock.setChangeType(Stock.ChangeTypeStatus.in);
			stockService.save(stock);
		}

	}

	@Autowired
	private StockService stockService;
	@Autowired
	private BoxCodeService boxCodeService;
	@Autowired
	private WarehousingBoxService warehousingBoxService;
	@Autowired
	private WarehousingItemService warehousingItemService;

}
