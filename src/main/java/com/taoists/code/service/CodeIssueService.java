package com.taoists.code.service;

import com.taoists.code.entity.CodeIssue;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public interface CodeIssueService extends BaseDao<CodeIssue> {

	void genCode(CodeIssue codeIssue);

}
