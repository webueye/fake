package com.taoists.ias.service;

import java.util.List;

import com.taoists.code.model.BoxModel;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.ias.entity.Warehousing;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
public interface WarehousingService extends BaseDao<Warehousing> {

	void saveByBoxCode(Warehousing warehousing, List<String> boxCodeValues);

	void save(Warehousing warehousing, List<BoxModel> boxModels);

}
