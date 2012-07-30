package com.taoists.code.service;

import java.util.List;

import com.taoists.code.entity.BoxCode;
import com.taoists.code.model.HistoryCodeModel;
import com.taoists.code.model.ImpResult;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
public interface CodeHistoryService extends BaseDao<BoxCode> {
	
	List<ImpResult> imp(List<String> lines, String summary);
	
	HistoryCodeModel prehandle(String summary, List<String> lines) ;

}
