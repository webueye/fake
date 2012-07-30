package com.taoists.code.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.taoists.base.entity.BoxSpec;
import com.taoists.base.entity.Product;
import com.taoists.base.service.BoxSpecService;
import com.taoists.base.service.ProductService;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.model.HistoryCodeModel;
import com.taoists.code.model.ImpResult;
import com.taoists.code.model.ImpResult.Type;
import com.taoists.code.model.SummaryModel;
import com.taoists.code.service.BoxCodeService;
import com.taoists.code.service.CodeHistoryService;
import com.taoists.code.service.FakeCodeService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
@Service("codeHistoryService")
public class CodeHistoryServiceImpl extends HibernateDaoSupport<BoxCode> implements CodeHistoryService {

	public static final String COMMA = ",";

	@Override
	@Transactional
	public List<ImpResult> imp(List<String> lines, String summary) {
		List<ImpResult> result = Lists.newArrayList();

		SummaryModel summaryModel = new SummaryModel(summary);
		if (!summaryModel.isComplete()) {
			result.add(new ImpResult(Type.other, summary, "illegal"));
			return result;
		}

		List<BoxSpec> boxSpecs = boxSpecService.ifNotExistCreate(summaryModel.getProductNo(), summaryModel.getActualNum());
		if (boxSpecs.isEmpty()) {
			result.add(new ImpResult(Type.other, summaryModel.getProductNo(), "product not existed"));
			return result;
		}

		BoxSpec boxSpec = boxSpecs.get(0);
		Multimap<String, String> group = group(lines, result);
		for (Entry<String, Collection<String>> entry : group.asMap().entrySet()) {
			BoxCode boxCode = boxCodeService.getByBoxCode(entry.getKey());
			if (boxCode != null) {
				result.add(new ImpResult(Type.box, entry.getKey(), "existed"));
				continue;
			}
			boxCode = new BoxCode();
			boxCode.setBoxSpec(boxSpec);
			boxCode.setBoxCode(entry.getKey());
			boxCode.setProduceDate(summaryModel.getDate());
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

	@Override
	public HistoryCodeModel prehandle(String summary, List<String> lines) {
		HistoryCodeModel model = new HistoryCodeModel();
		SummaryModel summaryModel = new SummaryModel(summary);
		if (!summaryModel.isComplete()) {
			model.setResult(new ImpResult(Type.other, summary, "illegal"));
			return model;
		}

		Product product = productService.getByProductNo(summaryModel.getProductNo());
		if (product == null) {
			model.setResult(new ImpResult(Type.other, summaryModel.getProductNo(), "product not existed"));
			return model;
		}

		List<ImpResult> illegals = Lists.newArrayList();
		Multimap<String, String> group = group(lines, illegals);
		model.setIllegals(illegals);
		List<HistoryCodeModel.Box> boxs = Lists.newArrayList();

		List<String> existedBoxCodes = boxCodeService.queryCodes(group.keySet());
		Map<String, String> boxCodeMap = Maps.newHashMap();
		for (String code : existedBoxCodes) {
			boxCodeMap.put(code, code);
		}
		List<String> existedPlainCodes = fakeCodeService.queryPlainCodes(group.values());
		Map<String, String> plainCodeMap = Maps.newHashMap();
		for (String code : existedPlainCodes) {
			plainCodeMap.put(code, code);
		}

		for (Entry<String, Collection<String>> entry : group.asMap().entrySet()) {
			HistoryCodeModel.Box box = new HistoryCodeModel.Box();
			if (boxCodeMap.get(entry.getKey()) != null) {
				box.setBoxCode(entry.getKey());
				box.setBoxCodeResult(new ImpResult(Type.box, entry.getKey(), "existed"));
			}
			box.setBoxCode(entry.getKey());
			box.setProduceDate(summaryModel.getDate());

			List<HistoryCodeModel.Fake> hFakes = Lists.newArrayList();

			for (String plainCode : entry.getValue()) {
				HistoryCodeModel.Fake hFake = new HistoryCodeModel.Fake();
				if (plainCodeMap.get(plainCode) != null) {
					hFake.setFakeResult(new ImpResult(Type.fake, plainCode, "existed"));
				}
				hFake.setPlainCode(plainCode);
				hFakes.add(hFake);
			}
			box.setFakes(hFakes);
			boxs.add(box);
		}
		model.setBoxs(boxs);
		return model;
	}

	private Multimap<String, String> group(List<String> lines, List<ImpResult> result) {
		Multimap<String, String> group = HashMultimap.create();
		for (String line : lines) {
			String[] columns = line.split(COMMA);
			if (columns.length >= 3) {
				if (StringUtils.isBlank(columns[0]) || StringUtils.isBlank(columns[1])) {
					result.add(new ImpResult(Type.other, line, "illegal"));
				} else if ("2".equals(columns[2])) {
					result.add(new ImpResult(Type.other, line, "enter"));
				} else {
					group.put(columns[1], columns[0]);
				}
			} else {
				result.add(new ImpResult(Type.other, line, "illegal"));
			}
		}
		return group;
	}

	@Autowired
	private BoxCodeService boxCodeService;
	@Autowired
	private FakeCodeService fakeCodeService;
	@Autowired
	private BoxSpecService boxSpecService;
	@Autowired
	private ProductService productService;

}
