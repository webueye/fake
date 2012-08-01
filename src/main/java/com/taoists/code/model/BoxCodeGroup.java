package com.taoists.code.model;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.taoists.code.entity.BoxCode;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-1
 */
public class BoxCodeGroup {

	private Company storageCompany;
	private LocalDate produceDate;
	private Collection<BoxCode> boxCodes;

	private Collection<BoxCodeGroup> groupCompany;

	public static List<BoxCodeGroup> getList(Multimap<LocalDate, BoxCode> group) {
		List<BoxCodeGroup> list = Lists.newArrayList();
		for (Entry<LocalDate, Collection<BoxCode>> entry : group.asMap().entrySet()) {
			BoxCodeGroup bg = new BoxCodeGroup();
			bg.setProduceDate(entry.getKey());
			bg.setBoxCodes(entry.getValue());
			bg.setGroupCompany(groupCompany(bg.getBoxCodes()));
			list.add(bg);
		}
		return list;
	}

	public static List<BoxCodeGroup> groupCompany(Collection<BoxCode> boxCodes) {
		Multimap<Company, BoxCode> group = HashMultimap.create();
		for (BoxCode boxCode : boxCodes) {
			group.put(boxCode.getStorageCompany(), boxCode);
		}
		List<BoxCodeGroup> list = Lists.newArrayList();
		if (CollectionUtils.isEmpty(boxCodes)) {
			return list;
		}
		for (Entry<Company, Collection<BoxCode>> entry : group.asMap().entrySet()) {
			BoxCodeGroup bg = new BoxCodeGroup();
			bg.setStorageCompany(entry.getKey());
			bg.setBoxCodes(entry.getValue());
			list.add(bg);
		}
		return list;
	}

	public int getSize() {
		if (CollectionUtils.isEmpty(boxCodes)) {
			return 1;
		}
		return boxCodes.size();
	}

	public Company getStorageCompany() {
		return storageCompany;
	}

	public void setStorageCompany(Company storageCompany) {
		this.storageCompany = storageCompany;
	}

	public void setProduceDate(LocalDate produceDate) {
		this.produceDate = produceDate;
	}

	public LocalDate getProduceDate() {
		return produceDate;
	}

	public Collection<BoxCode> getBoxCodes() {
		return boxCodes;
	}

	public void setBoxCodes(Collection<BoxCode> boxCodes) {
		this.boxCodes = boxCodes;
	}

	public void setGroupCompany(Collection<BoxCodeGroup> groupCompany) {
		this.groupCompany = groupCompany;
	}

	public Collection<BoxCodeGroup> getGroupCompany() {
		return groupCompany;
	}
}
