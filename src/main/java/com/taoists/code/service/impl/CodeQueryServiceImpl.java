package com.taoists.code.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.code.entity.CodeQuery;
import com.taoists.code.service.CodeQueryService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-3
 */
@Service
public class CodeQueryServiceImpl extends HibernateDaoSupport<CodeQuery> implements CodeQueryService {

}
