package com.taoists.sys.service;

import java.util.List;

import com.taoists.common.bean.Page;
import com.taoists.common.orm.dao.BaseDao;
import com.taoists.sys.entity.Account;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
public interface AccountService extends BaseDao<Account> {

	List<Account> findAccounts(Account account, Page page);

}
