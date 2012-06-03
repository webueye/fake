package com.taoists.sys.dao;

import java.util.List;

import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.entity.Account;

/**
 * @author rubys
 * @since 2012-5-29
 */
public interface AccountDao extends BaseDao<Account> {

	List<Account> findAccounts(Account account, Page page);

}
