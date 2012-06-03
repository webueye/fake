package com.taoists.sys.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.dao.AccountDao;
import com.taoists.sys.entity.Account;

/**
 * @author rubys
 * @since 2012-5-29
 */
@Repository("accountDao")
public class AccountDaoImpl extends HibernateDaoSupport<Account> implements AccountDao {
	
	public List<Account> findAccounts(Account account, Page page){
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(account));
		return findPage(detachedCriteria, page);
	}

}
