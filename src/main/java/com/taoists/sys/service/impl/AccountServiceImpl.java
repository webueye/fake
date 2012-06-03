package com.taoists.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.sys.dao.impl.AccountDaoImpl;
import com.taoists.sys.service.AccountService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@Transactional
@Service("accountService")
public class AccountServiceImpl extends AccountDaoImpl implements AccountService {


}
