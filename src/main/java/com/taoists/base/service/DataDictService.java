package com.taoists.base.service;

import java.util.List;

import com.taoists.base.entity.DataDict;
import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
public interface DataDictService extends BaseDao<DataDict> {

	List<DataDict> findDataDictByCategoryCode(String categoryCode);

	List<DataDict> findDataDictByCategoryCode(String categoryCode, Page page);

}
