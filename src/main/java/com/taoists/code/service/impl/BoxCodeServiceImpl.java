package com.taoists.code.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.entity.enums.BoxCodeStatus;
import com.taoists.code.model.BoxCodeGroup;
import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.FakeCodeService;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.PropertyFilter;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Transactional
@Service("boxCodeService")
public class BoxCodeServiceImpl extends HibernateDaoSupport<BoxCode> implements BoxCodeService {

	@Override
	@Transactional
	public void batchUpdate(Collection<BoxCode> boxCodes, BoxCodeStatus status, Company storageCompany) {
		for (BoxCode boxCode : boxCodes) {
			boxCode.setStatus(status);
			boxCode.setStatusCode(status.getCode());
			update(boxCode);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> queryCodes(Collection<String> boxCodes) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.in("boxCode", boxCodes));
		detachedCriteria.setProjection(Projections.groupProperty("boxCode"));
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BoxCode> queryBoxCodes(Collection<String> boxCodes) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.in("boxCode", boxCodes));
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}

	@Override
	@Transactional
	public Map<String, String> bind(List<String> codes) {
		logger.debug("codes[{}], size[{}]", codes, codes.size());

		Map<String, String> map = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(codes)) {
			String code = codes.get(codes.size() - 1);
			BoxCode boxCode = getByBoxCode(code);
			if (boxCode == null) {
				map.put("boxCodeNotExist", code);
				return map;
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < codes.size() - 1; i++) {
				FakeCode fakeCode = fakeCodeService.getByPlainCode(codes.get(i));
				if (fakeCode == null) {
					sb.append(codes.get(i)).append("\n");
					continue;
				}
				fakeCode.setBoxCode(boxCode);
				fakeCodeService.update(fakeCode);
			}
			if (StringUtils.isNotBlank(sb.toString())) {
				map.put("fakeCodeNotExist", sb.toString());
			} else {
				boxCode.setProduceDate(LocalDate.now());
			}
		}
		return map;
	}

	@Override
	public void fromFileToBind(List<String> lines) {
		Map<String, Set<String>> group = group(lines);
		for (Entry<String, Set<String>> entry : group.entrySet()) {
			BoxCode boxCode = getByBoxCode(entry.getKey());
			if (boxCode == null) {
				continue;
			}
			for (String plainCode : entry.getValue()) {
				FakeCode fakeCode = fakeCodeService.getByPlainCode(plainCode);
				if (fakeCode == null) {
					continue;
				}
				if (fakeCode.getBoxCode() == null) {
					fakeCode.setBoxCode(boxCode);
					fakeCodeService.update(fakeCode);
				}
			}
			boxCode.setProduceDate(LocalDate.now());
		}
	}

	@Override
	public BoxCode getByBoxCode(String boxCode) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("boxCode", boxCode));
		return (BoxCode) detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BoxCode> findBoxCodes(List<String> boxCodes) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.in("boxCode", boxCodes));
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getCodeRange(Serializable codeIssueId) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("codeIssue.id", codeIssueId));
		criteria.setProjection(Projections.projectionList().add(Projections.min("boxCode")).add(Projections.max("boxCode")));
		return criteria.getExecutableCriteria(getSession()).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BoxCode> findBoxCodes(long codeIssueId, String startCode, String endCode) {
		DetachedCriteria criteria = createDetachedCriteria();
		criteria.add(Restrictions.eq("codeIssue.id", codeIssueId));
		criteria.add(Restrictions.between("boxCode", startCode, endCode));
		return criteria.getExecutableCriteria(getSession()).list();
	}

	@SuppressWarnings("unchecked")
	public List<BoxCodeGroup> batchTrack(Page page, List<PropertyFilter> filters) {
		String propertyName = "produceDate";
		Criteria criteria = createCriteria(buildCriterionByPropertyFilter(filters));
		criteria.setProjection(Projections.groupProperty(propertyName));
		criteria.addOrder(Order.asc(propertyName));
		createAlias(criteria, filters);
		List<LocalDate> dates = criteria.list();
		if (CollectionUtils.isNotEmpty(dates)) {
			page.setTotalCount(dates.size());
			List<LocalDate> subDates = dates.subList(page.getBeginIndex(), page.getEndIndex() > dates.size() ? dates.size() : page.getEndIndex());
			criteria = createCriteria(buildCriterionByPropertyFilter(filters));
			criteria.add(Restrictions.in(propertyName, subDates));
			createAlias(criteria, filters);
			List<BoxCode> boxCodes = criteria.list();
			Multimap<LocalDate, BoxCode> group = HashMultimap.create();
			for (BoxCode boxCode : boxCodes) {
				group.put(boxCode.getProduceDate(), boxCode);
			}
			page.setDatas(BoxCodeGroup.getList(group));
			return (List<BoxCodeGroup>) page.getDatas();
		} else {
			return Lists.newArrayList();
		}
	}

	private static final int BOX_CODE_LENGTH = 12;

	private Map<String, Set<String>> group(List<String> lines) {
		Set<String> fakes = Sets.newHashSet();
		Map<String, Set<String>> prepareGroup = Maps.newLinkedHashMap();
		for (String line : lines) {
			if (StringUtils.isBlank(line)) {
				continue;
			}
			if (line.trim().length() == BOX_CODE_LENGTH) {
				prepareGroup.put(line.trim(), fakes);
				fakes = Sets.newHashSet();
			} else {
				fakes.add(line.trim());
			}
		}

		Entry<String, Set<String>> previousEntry = null;
		Map<String, Set<String>> grouped = Maps.newLinkedHashMap();
		for (Entry<String, Set<String>> entry : prepareGroup.entrySet()) {
			if (CollectionUtils.isEmpty(entry.getValue())) {
				if (previousEntry != null && CollectionUtils.isNotEmpty(previousEntry.getValue())) {
					grouped.remove(previousEntry.getKey());
					grouped.put(entry.getKey(), previousEntry.getValue());
					entry.setValue(previousEntry.getValue());
				}
			} else {
				grouped.put(entry.getKey(), entry.getValue());
			}
			previousEntry = entry;
		}
		return grouped;
	}

	@Autowired
	private FakeCodeService fakeCodeService;

}
