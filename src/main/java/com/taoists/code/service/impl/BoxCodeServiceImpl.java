package com.taoists.code.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taoists.code.dao.impl.BoxCodeDaoImpl;
import com.taoists.code.service.BoxCodeService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-2
 */
@Transactional
@Service("boxCodeService")
public class BoxCodeServiceImpl extends BoxCodeDaoImpl implements BoxCodeService {

}
