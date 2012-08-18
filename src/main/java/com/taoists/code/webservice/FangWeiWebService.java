package com.taoists.code.webservice;

import javax.jws.WebService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-18
 */
@WebService
public interface FangWeiWebService {

	int queryFangWeiCode(int queryWay, String code, String userNo, String userPhone);

	int queryStickCode(int queryWay, String code, String userNo, String userPhone);

}
