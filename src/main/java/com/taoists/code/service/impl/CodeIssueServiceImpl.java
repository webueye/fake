package com.taoists.code.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.taoists.base.entity.BoxSpec;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.BoxCodeStatus;
import com.taoists.code.entity.CodeIssue;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.service.CodeIssueService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.common.util.CodeUtils;
import com.taoists.common.util.DateUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@Service("codeRuleDao")
public class CodeIssueServiceImpl extends HibernateDaoSupport<CodeIssue> implements CodeIssueService {

	@Override
	@Transactional
	public void genCode(CodeIssue codeIssue) {
		save(codeIssue);

		if (codeIssue.getCodeType()) {
			genBoxCode(codeIssue);
		} else {
			genFakeCode(codeIssue);
		}
	}

	private void genBoxCode(CodeIssue codeIssue) {
		for (int i = 0; i < codeIssue.getIssueCount(); i++) {
			BoxCode boxCode = new BoxCode();
			boxCode.setCodeIssue(codeIssue);
			boxCode.setBoxCode(genBoxCodeValue(codeIssue.getBoxSpec()));
			boxCode.setBoxSpec(codeIssue.getBoxSpec());
			boxCode.setCreationCompanyId(codeIssue.getCompanyId());
			boxCode.setStorageCompanyId(codeIssue.getCompanyId());
			boxCode.setStatus(BoxCodeStatus.generate);
			insert(boxCode);
		}
	}

	private void genFakeCode(CodeIssue codeIssue) {
		for (int i = 0; i < codeIssue.getIssueCount(); i++) {
			FakeCode fakeCode = new FakeCode();
			fakeCode.setCodeIssue(codeIssue);
			fakeCode.setBoxSpec(codeIssue.getBoxSpec());
			fakeCode.setFakeCode(CodeUtils.genCode(codeIssue.getCodeLength()));
			fakeCode.setPlainCode(CodeUtils.genCode(codeIssue.getCodeLength()));
			fakeCode.setStatus(codeIssue.getStatus());
			insert(fakeCode);
		}
	}

	private String genBoxCodeValue(BoxSpec boxSpec) {
		String dateStr = DateUtils.todayToString();
		Integer num = cache.getUnchecked(dateStr);
		cache.put(dateStr, num + 1);
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(boxSpec.getSpecNo())) {
			sb.append(boxSpec.getSpecNo());
		}
		sb.append(dateStr);

		String numStr = num.toString();
		for (int i = numStr.length(); i < 6; i++) {
			sb.append("0");
		}
		sb.append(numStr);
		return sb.toString();
	}

	private LoadingCache<String, Integer> cache = CacheBuilder.newBuilder().build(new CacheLoader<String, Integer>() {

		@Override
		public Integer load(String key) throws Exception {
			return 1;
		}

	});

}
