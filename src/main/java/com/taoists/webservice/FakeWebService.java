package com.taoists.webservice;

import javax.jws.WebService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-3
 */
@WebService
public interface FakeWebService {
	
	int queryFakeCode(int queryWay, String fakeCode, String userNo, String userPhone);

}
