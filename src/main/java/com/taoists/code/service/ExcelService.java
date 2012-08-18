package com.taoists.code.service;

import com.taoists.code.entity.CodeIssue;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-22
 */
public interface ExcelService {

	byte[] exportBoxCodes(CodeIssue codeIssue);

	byte[] exportFakeCodes(CodeIssue codeIssue);
	
	byte[] exportCodes(CodeIssue codeIssue);

}
