package com.taoists.code.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.model.ImpResult;
import com.taoists.code.model.ImpResult.Type;
import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.CodeHistoryService;
import com.taoists.code.service.FakeCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.common.util.DateUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
@Service
public class CodeHistoryServiceImpl extends HibernateDaoSupport<BoxCode> implements CodeHistoryService {

	public static final String COMMA = ",";

	@Override
	@Transactional
	public List<ImpResult> imp(List<String> lines) {
		List<ImpResult> result = Lists.newArrayList();
		Multimap<String, String> group = group(lines, result);
		for (Entry<String, Collection<String>> entry : group.asMap().entrySet()) {
			BoxCode boxCode = boxCodeService.getByBoxCode(entry.getKey());
			if (boxCode != null) {
				result.add(new ImpResult(Type.box, entry.getKey(), "existed"));
				continue;
			}
			boxCode = new BoxCode();
			boxCode.setBoxCode(entry.getKey());
			boxCode.setProduceDate(getProduceDate(entry.getKey()));
			boxCodeService.save(boxCode);

			for (String plainCode : entry.getValue()) {
				FakeCode fake = fakeCodeService.getByPlainCode(plainCode);
				if (fake != null) {
					result.add(new ImpResult(Type.fake, plainCode, "existed"));
					continue;
				}
				fake = new FakeCode();
				fake.setBoxCode(boxCode);
				fake.setPlainCode(plainCode);
				fake.setFakeCode(plainCode);
				fakeCodeService.save(fake);
			}
		}
		return result;
	}

	private Multimap<String, String> group(List<String> lines, List<ImpResult> result) {
		Multimap<String, String> group = HashMultimap.create();
		for (String line : lines) {
			String[] columns = line.split(COMMA);
			if (columns.length >= 3) {
				if (StringUtils.isBlank(columns[0]) || StringUtils.isBlank(columns[1])) {
					result.add(new ImpResult(Type.other, line, "illegal"));
				} else {
					group.put(columns[1], columns[0]);
				}
			} else {
				result.add(new ImpResult(Type.other, line, "illegal"));
			}
		}
		return group;
	}

	private LocalDate getProduceDate(String boxCode) {
		String date = boxCode.substring(0, 6);
		return LocalDate.parse(date, DateTimeFormat.forPattern(DateUtils.YYMMDD));
	}

	@Autowired
	private BoxCodeService boxCodeService;
	@Autowired
	private FakeCodeService fakeCodeService;

}
