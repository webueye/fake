package com.taoists.sys.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.sys.entity.Role;
import com.taoists.sys.service.RoleService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-19
 */
@Service
public class RoleServiceImpl extends HibernateDaoSupport<Role> implements RoleService {

}
