package com.taoists.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.base.entity.BoxSpec;
import com.taoists.base.service.BoxSpecService;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.BoxCodeStatus;
import com.taoists.code.entity.CodeIssue;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.service.CodeIssueService;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.common.util.CodeUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@Service("codeIssueService")
public class CodeIssueServiceImpl extends HibernateDaoSupport<CodeIssue> implements CodeIssueService {

	@Override
	@Transactional
	public void genCode(CodeIssue codeIssue) {
		save(codeIssue);
		Long boxCodeBatchNum = super.count("codeType", Boolean.TRUE);
		Long fakeCodeBatchNum = super.count("codeType", Boolean.FALSE);
		if (codeIssue.getCodeType()) {
			genBoxCode(codeIssue, boxCodeBatchNum);
		} else {
			genFakeCode(codeIssue, fakeCodeBatchNum);
		}
	}

	private void genBoxCode(CodeIssue codeIssue, Long batchNum) {
		BoxSpec boxSpec = boxSpecService.get(codeIssue.getBoxSpec().getId());

		String codePrefix = CodeUtils.getCodePrefix(batchNum);
		int code = 10000;
		for (int i = 0; i < codeIssue.getIssueCount(); i++) {
			BoxCode boxCode = new BoxCode();
			boxCode.setCodeIssue(codeIssue);
			boxCode.setBoxCode(codePrefix + boxSpec.getSpecNo() + (++code));
			boxCode.setBoxSpec(boxSpec);
			boxCode.setCreationCompany(codeIssue.getCreationCompany());
			boxCode.setStorageCompany(codeIssue.getCreationCompany());
			boxCode.setStatus(BoxCodeStatus.generate);
			boxCode.setStatusCode(BoxCodeStatus.generate.getCode());
			insert(boxCode);
		}
	}

	private void genFakeCode(CodeIssue codeIssue, Long batchNum) {
		String codePrefix = CodeUtils.getCodePrefix(batchNum);
		int plainCode = 100000;
		for (int i = 0; i < codeIssue.getIssueCount(); i++) {
			FakeCode fakeCode = new FakeCode();
			fakeCode.setCodeIssue(codeIssue);
			fakeCode.setBoxSpec(codeIssue.getBoxSpec());
			String randomCode = CodeUtils.genCode(7);
			fakeCode.setFakeCode("0" + randomCode.substring(0, 3) + codePrefix + randomCode.substring(3, 7));
			fakeCode.setPlainCode(codePrefix + (++plainCode));
			fakeCode.setStatus(codeIssue.getStatus());
			insert(fakeCode);
		}
	}

	@Autowired
	private BoxSpecService boxSpecService;

}
