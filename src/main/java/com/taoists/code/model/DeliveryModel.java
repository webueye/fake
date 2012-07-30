package com.taoists.code.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.taoists.code.entity.BoxCode;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-28
 */
public class DeliveryModel {

	private String deliveryNo;
	private String companyNo;

	private List<BoxCode> boxCodes;
	private Collection<String> codes;

	private int boxSize;
	private int totalSize;

	private ImpResult impResult;

	public static Multimap<String, DeliveryModel> group(Collection<DeliveryModel> deliveryModels) {
		Multimap<String, DeliveryModel> group = HashMultimap.create();
		for (DeliveryModel delivery : deliveryModels) {
			if (delivery.getImpResult() != null) {
				group.put("illegals", delivery);
				continue;
			}
			if (CollectionUtils.isNotEmpty(delivery.getBoxCodes())) {
				Set<String> codes = Sets.newHashSet();
				for (BoxCode boxCode : delivery.getBoxCodes()) {
					codes.add(boxCode.getBoxCode());
				}
				group.put("existed", delivery);
				delivery.getCodes().removeAll(codes);
				if (CollectionUtils.isNotEmpty(delivery.getCodes())) {
					group.put("notExisted", delivery);
				}
			} else {
				group.put("notExisted", delivery);
			}
		}
		return group;
	}

	public int getCodeSize() {
		if (codes != null) {
			return codes.size();
		}
		return 1;
	}

	public int getBoxCodeSize() {
		if (boxCodes != null) {
			return boxCodes.size();
		}
		return 1;
	}

	public DeliveryModel(String deliveryNo, String companyNo) {
		this.deliveryNo = deliveryNo;
		this.companyNo = companyNo;
	}

	public void setImpResult(ImpResult impResult) {
		this.impResult = impResult;
	}

	public ImpResult getImpResult() {
		return impResult;
	}

	public void setCodes(Collection<String> codes) {
		this.codes = codes;
	}

	public Collection<String> getCodes() {
		return codes;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public List<BoxCode> getBoxCodes() {
		return boxCodes;
	}

	public void setBoxCodes(List<BoxCode> boxCodes) {
		this.boxCodes = boxCodes;
	}

	public int getBoxSize() {
		return boxSize;
	}

	public void setBoxSize(int boxSize) {
		this.boxSize = boxSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyNo == null) ? 0 : companyNo.hashCode());
		result = prime * result + ((deliveryNo == null) ? 0 : deliveryNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryModel other = (DeliveryModel) obj;
		if (companyNo == null) {
			if (other.companyNo != null)
				return false;
		} else if (!companyNo.equals(other.companyNo))
			return false;
		if (deliveryNo == null) {
			if (other.deliveryNo != null)
				return false;
		} else if (!deliveryNo.equals(other.deliveryNo))
			return false;
		return true;
	}

}
