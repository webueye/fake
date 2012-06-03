package com.taoists.code.service;

import com.taoists.code.dao.CodeIssueDao;
import com.taoists.code.entity.CodeIssue;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public interface CodeIssueService extends CodeIssueDao {

	void genCode(CodeIssue codeIssue);

}
