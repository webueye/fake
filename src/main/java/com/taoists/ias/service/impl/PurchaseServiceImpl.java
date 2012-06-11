package com.taoists.ias.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.service.BoxCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.ias.controller.PurchaseDetailModel;
import com.taoists.ias.entity.Purchase;
import com.taoists.ias.entity.PurchaseBox;
import com.taoists.ias.entity.PurchaseItem;
import com.taoists.ias.entity.PurchaseStatus;
import com.taoists.ias.service.PurchaseBoxService;
import com.taoists.ias.service.PurchaseItemService;
import com.taoists.ias.service.PurchaseService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-9
 */
@Service("purchaseService")
public class PurchaseServiceImpl extends HibernateDaoSupport<Purchase> implements PurchaseService {

	public void save(Purchase purchase, String[] boxCodeValues) {
		purchase.setStatus(PurchaseStatus.create);
		this.save(purchase);

		List<BoxCode> boxCodes = boxCodeService.findBoxCodes(Arrays.asList(boxCodeValues));
		List<PurchaseDetailModel> models = PurchaseDetailModel.groupByProduct(boxCodes);
		for (PurchaseDetailModel model : models) {
			PurchaseItem purchaseItem = new PurchaseItem();
			purchaseItem.setPurchase(purchase);
			purchaseItem.setProduct(model.getProduct());
			purchaseItem.setActualQty(model.getTotalCount());
			purchaseItemService.save(purchaseItem);

			for (BoxCode boxCode : model.getBoxCodes()) {
				PurchaseBox purchaseBox = new PurchaseBox();
				purchaseBox.setBoxCode(boxCode);
				purchaseBox.setPurchase(purchase);
				purchaseBoxService.save(purchaseBox);
			}
		}
	}

	private BoxCodeService boxCodeService;
	private PurchaseBoxService purchaseBoxService;
	private PurchaseItemService purchaseItemService;

	public void setBoxCodeService(BoxCodeService boxCodeService) {
		this.boxCodeService = boxCodeService;
	}

	public void setPurchaseBoxService(PurchaseBoxService purchaseBoxService) {
		this.purchaseBoxService = purchaseBoxService;
	}

	public void setPurchaseItemService(PurchaseItemService purchaseItemService) {
		this.purchaseItemService = purchaseItemService;
	}

}
