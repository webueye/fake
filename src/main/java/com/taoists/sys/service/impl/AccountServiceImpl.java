package com.taoists.sys.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.entity.Account;
import com.taoists.sys.service.AccountService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@Transactional
@Service("accountService")
public class AccountServiceImpl extends HibernateDaoSupport<Account> implements AccountService {

	@Override
	public List<Account> findAccounts(Account account, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(account));
		return findPage(detachedCriteria, page);
	}

}
