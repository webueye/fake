package com.taoists.code.service.impl;

import org.springframework.stereotype.Service;

import com.taoists.code.dao.impl.FakeCodeDaoImpl;
import com.taoists.code.service.FakeCodeService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
@Service("fakeCodeService")
public class FakeCodeServiceImpl extends FakeCodeDaoImpl implements FakeCodeService {

}
