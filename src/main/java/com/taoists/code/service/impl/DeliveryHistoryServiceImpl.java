package com.taoists.code.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.taoists.code.model.DeliveryModel;
import com.taoists.code.model.ImpResult;
import com.taoists.code.model.ImpResult.Type;
import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.DeliveryHistoryService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;
import com.taoists.crm.service.CompanyService;
import com.taoists.ias.entity.Purchase;
import com.taoists.ias.entity.PurchaseStatus;
import com.taoists.ias.service.PurchaseService;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-28
 */
@Service
public class DeliveryHistoryServiceImpl extends HibernateDaoSupport<Purchase> implements DeliveryHistoryService {

	@Override
	public void preview(Collection<DeliveryModel> deliveryModels) {
		for (DeliveryModel deliveryModel : deliveryModels) {
			Purchase purchase = purchaseService.getByPurchaseNo(deliveryModel.getDeliveryNo());
			if (purchase != null) {
				deliveryModel.setImpResult(new ImpResult(Type.out, deliveryModel.getDeliveryNo(), "purchase existed"));
				continue;
			}
			Company company = companyService.getByCompanyNo(deliveryModel.getCompanyNo());
			if (company == null) {
				deliveryModel.setImpResult(new ImpResult(Type.out, deliveryModel.getCompanyNo(), "compnay not existed"));
				continue;
			}
			deliveryModel.setBoxCodes(boxCodeService.queryBoxCodes(deliveryModel.getCodes()));
		}
	}

	@Override
	@Transactional
	public List<ImpResult> imp(Collection<DeliveryModel> deliveryModels, Account account) {
		List<ImpResult> impResults = Lists.newArrayList();
		for (DeliveryModel deliveryModel : deliveryModels) {
			Purchase purchase = purchaseService.getByPurchaseNo(deliveryModel.getDeliveryNo());
			if (purchase != null) {
				impResults.add(new ImpResult(Type.out, deliveryModel.getDeliveryNo(), "purchase existed"));
				continue;
			}
			Company company = companyService.getByCompanyNo(deliveryModel.getCompanyNo());
			if (company == null) {
				impResults.add(new ImpResult(Type.out, deliveryModel.getCompanyNo(), "compnay not existed"));
				continue;
			}
			List<String> boxCodes = boxCodeService.queryCodes(deliveryModel.getCodes());
			if (CollectionUtils.isNotEmpty(boxCodes)) {
				Purchase pur = new Purchase();
				pur.setCreaterId(account.getId());
				pur.setCreater(account.getNickname());
				pur.setSupplierCompany(new Company(account.getCompanyId()));
				pur.setPurchaseCompany(company);

				purchaseService.save(pur, boxCodes.toArray(new String[boxCodes.size()]));
				purchaseService.udpateStatus(pur, PurchaseStatus.inTransit.getCode(), account);
			}
			Collection<String> codes = deliveryModel.getCodes();
			codes.removeAll(boxCodes);
			if (CollectionUtils.isNotEmpty(codes)) {
				for (String code : codes) {
					impResults.add(new ImpResult(Type.box, code, "box code not existed"));
				}
			}
		}
		return impResults;
	}

	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private BoxCodeService boxCodeService;

}
