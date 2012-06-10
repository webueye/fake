package com.taoists.base.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.base.entity.BoxSpec;
import com.taoists.base.service.BoxSpecService;
import com.taoists.common.orm.dao.HibernateDaoSupport;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-1
 */
@Service("boxSpecService")
public class BoxSpecServiceImpl extends HibernateDaoSupport<BoxSpec> implements BoxSpecService {

}
