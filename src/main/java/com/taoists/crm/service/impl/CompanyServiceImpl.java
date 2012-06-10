package com.taoists.crm.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.crm.entity.Company;
import com.taoists.crm.service.CompanyService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Service("companyService")
public class CompanyServiceImpl extends HibernateDaoSupport<Company> implements CompanyService {

}
