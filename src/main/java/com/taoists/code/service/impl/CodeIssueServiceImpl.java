package com.taoists.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.base.entity.BoxSpec;
import com.taoists.base.service.BoxSpecService;
import com.taoists.code.entity.BoxCode;
import com.taoists.code.entity.CodeIssue;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.entity.FangWeiCode;
import com.taoists.code.entity.enums.BoxCodeStatus;
import com.taoists.code.entity.enums.CodeIssueTypeEnum;
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
		if (CodeIssueTypeEnum.boxCode.value().equals(codeIssue.getCodeType())) {
			save(codeIssue);
			Long boxCodeBatchNum = super.count("codeType", CodeIssueTypeEnum.boxCode.value());
			genBoxCode(codeIssue, boxCodeBatchNum);
		} else if (CodeIssueTypeEnum.fakeCode.value().equals(codeIssue.getCodeType())) {
			codeIssue.setBoxSpec(null);
			save(codeIssue);
			Long fakeCodeBatchNum = super.count("codeType", CodeIssueTypeEnum.fakeCode.value());
			genFakeCode(codeIssue, fakeCodeBatchNum);
		} else {
			codeIssue.setBoxSpec(null);
			save(codeIssue);
			Long fangWeiCodeBatchNum = super.count("codeType", CodeIssueTypeEnum.fangWeiCode.value());
			genFangWeiCode(codeIssue, fangWeiCodeBatchNum);
		}
	}

	private void genBoxCode(CodeIssue codeIssue, Long batchNum) {
		BoxSpec boxSpec = boxSpecService.get(codeIssue.getBoxSpec().getId());

		String codePrefix = CodeUtils.getCodePrefix(batchNum);
		int code = 0;
		int length = 5;
		for (int i = 0; i < codeIssue.getIssueCount(); i++) {
			BoxCode boxCode = new BoxCode();
			boxCode.setCodeIssue(codeIssue);
			boxCode.setBoxCode(codePrefix + boxSpec.getSpecNo() + CodeUtils.fillZero(String.valueOf((++code)), length));
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
		int plainCode = 0;
		int length = 6;
		for (int i = 0; i < codeIssue.getIssueCount(); i++) {
			FakeCode fakeCode = new FakeCode();
			fakeCode.setCodeIssue(codeIssue);
			// fakeCode.setBoxSpec(codeIssue.getBoxSpec());
			String randomCode = CodeUtils.genCode(7);
			fakeCode.setFakeCode("0" + randomCode.substring(0, 3) + codePrefix + randomCode.substring(3, 7));
			fakeCode.setPlainCode(codePrefix + CodeUtils.fillZero(String.valueOf((++plainCode)), length));
			fakeCode.setStatus(codeIssue.getStatus());
			insert(fakeCode);
		}
	}

	private void genFangWeiCode(CodeIssue codeIssue, Long batchNum) {
		String codePrefix = CodeUtils.getCodePrefix(batchNum);
		for (int i = 0; i < codeIssue.getIssueCount(); i++) {
			FangWeiCode fangWeiCode = new FangWeiCode();
			fangWeiCode.setCodeType(codeIssue.getCodeType());
			fangWeiCode.setCodeIssue(codeIssue);

			String randomCode = CodeUtils.genCode(7);
			if (CodeIssueTypeEnum.fangWeiCode.value().equals(codeIssue.getCodeType())) {
				fangWeiCode.setCodeNo("0" + randomCode.substring(0, 3) + codePrefix + randomCode.substring(3, 7));
			} else {
				fangWeiCode.setCodeNo("1" + randomCode.substring(0, 3) + codePrefix + randomCode.substring(3, 7));
			}
			fangWeiCode.setStatus(codeIssue.getStatus());
			insert(fangWeiCode);
		}
	}

	@Autowired
	private BoxSpecService boxSpecService;

}
