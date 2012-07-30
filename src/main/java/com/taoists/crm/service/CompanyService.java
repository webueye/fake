package com.taoists.crm.service;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.crm.controller.ContactModel;
import com.taoists.crm.entity.Company;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
public interface CompanyService extends BaseDao<Company> {

	Company getByCompanyNo(String companyNo);

	void save(Company company, ContactModel contactModel);

}
