package com.taoists.ias.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.BoxCodeStatus;
import com.taoists.code.model.BoxModel;
import com.taoists.code.service.BoxCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.ias.entity.Stock.ChangeTypeStatus;
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
	@Transactional
	public void saveByBoxCode(Warehousing warehousing, List<String> boxCodeValues) {
		save(warehousing);

		List<BoxCode> boxCodes = boxCodeService.findBoxCodes(boxCodeValues);
		List<BoxModel> boxModels = BoxModel.groupByProduct(boxCodes);

		save(warehousing, boxModels);
		stockService.saveStock(boxModels, warehousing.getCompany(), ChangeTypeStatus.in);
	}

	@Override
	@Transactional
	public void save(Warehousing warehousing, List<BoxModel> boxModels) {
		save(warehousing);
		for (BoxModel model : boxModels) {
			WarehousingItem item = new WarehousingItem();
			item.setWarehousing(warehousing);
			item.setMemo(warehousing.getMemo());
			item.setProduct(model.getProduct());
			item.setPrice(model.getProduct().getMarketPrice());
			item.setQty(model.getBoxCount());
			item.setSubAmount(model.getProduct().getMarketPrice().multiply(new BigDecimal(model.getBoxCount())));
			warehousingItemService.save(item);

			for (BoxCode boxCode : model.getBoxCodes()) {
				boxCode.setStorageCompany(warehousing.getCompany());
				boxCode.setStatus(BoxCodeStatus.warehousing);
				boxCode.setStatusCode(BoxCodeStatus.warehousing.getCode());
				boxCodeService.update(boxCode);

				WarehousingBox box = new WarehousingBox();
				box.setBoxCode(boxCode);
				box.setWarehousing(warehousing);
				warehousingBoxService.save(box);
			}
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
