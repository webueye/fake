package com.taoists.ias.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.enums.BoxCodeStatus;
import com.taoists.code.model.BoxModel;
import com.taoists.code.service.BoxCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;
import com.taoists.ias.entity.Purchase;
import com.taoists.ias.entity.PurchaseBox;
import com.taoists.ias.entity.PurchaseItem;
import com.taoists.ias.entity.PurchaseStatus;
import com.taoists.ias.entity.PurchaseTypeEnum;
import com.taoists.ias.service.PurchaseBoxService;
import com.taoists.ias.service.PurchaseItemService;
import com.taoists.ias.service.PurchaseService;
import com.taoists.ias.service.StockService;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-9
 */
@Service("purchaseService")
public class PurchaseServiceImpl extends HibernateDaoSupport<Purchase> implements PurchaseService {

	@Override
	public Purchase getByPurchaseNo(String purchaseNo) {
		List<Purchase> purchases = findDatas("purchaseNo", purchaseNo);
		if (CollectionUtils.isNotEmpty(purchases)) {
			return purchases.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void save(Purchase purchase, List<String> boxCodeValues) {
		purchase.setStatus(PurchaseStatus.create);
		purchase.setStatusCode(PurchaseStatus.create.getCode());
		this.save(purchase);

		List<BoxCode> boxCodes = boxCodeService.findBoxCodes(boxCodeValues);
		List<BoxModel> models = BoxModel.groupByProduct(boxCodes);
		for (BoxModel model : models) {
			PurchaseItem purchaseItem = new PurchaseItem();
			purchaseItem.setPurchase(purchase);
			purchaseItem.setProduct(model.getProduct());
			purchaseItem.setActualQty(model.getTotalCount());
			purchaseItemService.save(purchaseItem);

			for (BoxCode boxCode : model.getBoxCodes()) {
				PurchaseBox purchaseBox = new PurchaseBox();
				purchaseBox.setBoxCode(boxCode);
				purchaseBox.setPurchase(purchase);
				purchaseBox.setPurchaseItem(purchaseItem);
				purchaseBoxService.save(purchaseBox);
			}
		}
	}

	@Override
	@Transactional
	public void returnedPurchase(Purchase purchase, List<String> boxCodeValues, Account account) {
		purchase.setStatus(PurchaseStatus.inTransit);
		purchase.setStatusCode(PurchaseStatus.inTransit.getCode());
		purchase.setPurchaseType(PurchaseTypeEnum.returnedPurchase.getCode());
		this.save(purchase);

		List<BoxCode> boxCodes = boxCodeService.findBoxCodes(boxCodeValues);
		List<BoxModel> models = BoxModel.groupByProduct(boxCodes);
		for (BoxModel model : models) {
			PurchaseItem purchaseItem = new PurchaseItem();
			purchaseItem.setPurchase(purchase);
			purchaseItem.setProduct(model.getProduct());
			purchaseItem.setActualQty(model.getTotalCount());
			purchaseItemService.save(purchaseItem);

			for (BoxCode boxCode : model.getBoxCodes()) {
				PurchaseBox purchaseBox = new PurchaseBox();
				purchaseBox.setBoxCode(boxCode);
				purchaseBox.setPurchase(purchase);
				purchaseBox.setPurchaseItem(purchaseItem);
				purchaseBoxService.save(purchaseBox);
			}
		}
		updateStatus(purchase, PurchaseStatus.inTransit.getCode(), account);
	}

	@Override
	@Transactional
	public void updateStatus(Purchase purchase, int state, Account account) {
		Purchase pur = get(purchase.getId());
		if (PurchaseStatus.inTransit.getCode() == state) {
			pur.setDeliveryMemo(purchase.getDeliveryMemo());
			pur.setStatus(PurchaseStatus.inTransit);
			pur.setStatusCode(PurchaseStatus.inTransit.getCode());
			pur.setDeliveryDateTime(new DateTime());
			pur.setDeliveryId(account.getId());
			pur.setDeliveryName(account.getNickname());
		} else if (PurchaseStatus.receive.getCode() == state) {
			pur.setArrivalMemo(purchase.getArrivalMemo());
			pur.setStatus(PurchaseStatus.receive);
			pur.setStatusCode(PurchaseStatus.receive.getCode());
			pur.setArrivalDateTime(new DateTime());
			pur.setArrivalId(account.getId());
			pur.setArrivalName(account.getNickname());
		}
		saveOrUpdate(pur);

		if (PurchaseStatus.inTransit.getCode() == pur.getStatus().getCode()) {
			stockService.outStock(BoxModel.groupByProduct(getBoxCodes(pur)), account);

			boxCodeService.batchUpdate(getBoxCodes(pur), BoxCodeStatus.inTransit, new Company(account.getCompany().getId()));
		} else if (PurchaseStatus.receive.getCode() == pur.getStatus().getCode()) {
			stockService.inStock(BoxModel.groupByProduct(getBoxCodes(pur)), account);

			boxCodeService.batchUpdate(getBoxCodes(pur), BoxCodeStatus.warehousing, new Company(account.getCompany().getId()));
		}
	}

	private List<BoxCode> getBoxCodes(Purchase purchase) {
		List<PurchaseBox> purchaseBoxs = purchaseBoxService.findDatas("purchase.id", purchase.getId());
		List<BoxCode> boxCodes = Lists.newArrayList();
		for (PurchaseBox purchaseBox : purchaseBoxs) {
			boxCodes.add(purchaseBox.getBoxCode());
		}
		return boxCodes;
	}

	@Autowired
	private StockService stockService;
	@Autowired
	private BoxCodeService boxCodeService;
	@Autowired
	private PurchaseBoxService purchaseBoxService;
	@Autowired
	private PurchaseItemService purchaseItemService;

}
