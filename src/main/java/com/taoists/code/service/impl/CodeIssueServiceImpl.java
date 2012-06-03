package com.taoists.code.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.code.dao.impl.CodeIssueDaoImpl;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.BoxCodeStatus;
import com.taoists.code.entity.CodeIssue;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.service.CodeIssueService;
import com.taoists.common.util.CodeUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@Service("codeRuleDao")
public class CodeIssueServiceImpl extends CodeIssueDaoImpl implements CodeIssueService {

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
			boxCode.setBoxCode(CodeUtils.genCode(codeIssue.getCodeLength()));
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

}
