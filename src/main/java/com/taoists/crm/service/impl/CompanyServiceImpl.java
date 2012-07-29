package com.taoists.crm.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.controller.ContactModel;
import com.taoists.crm.entity.Company;
import com.taoists.crm.entity.ContactType;
import com.taoists.crm.service.CompanyService;
import com.taoists.crm.service.ContactService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Service("companyService")
public class CompanyServiceImpl extends HibernateDaoSupport<Company> implements CompanyService {

	@Override
	public Company getByCompanyNo(String companyNo) {
		List<Company> companies = findDatas("compnayNo", companyNo);
		if (CollectionUtils.isNotEmpty(companies)) {
			return companies.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void save(Company company, ContactModel contactModel) {
		save(company);
		contactModel.getCorporation().setContactType(ContactType.boss);
		contactModel.getBusiness().setContactType(ContactType.business);
		contactModel.getCorporation().setCompany(company);
		contactModel.getBusiness().setCompany(company);
		contactService.save(contactModel.getCorporation());
		contactService.save(contactModel.getBusiness());
	}

	@Autowired
	private ContactService contactService;

}
