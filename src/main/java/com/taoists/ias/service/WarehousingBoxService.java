package com.taoists.ias.service;

import java.util.List;

import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.crm.entity.Company;
import com.taoists.ias.entity.WarehousingBox;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-12
 */
public interface WarehousingBoxService extends BaseDao<WarehousingBox> {

	List<WarehousingBox> findWarehousings(String boxCode, Company company, Page page);

}
