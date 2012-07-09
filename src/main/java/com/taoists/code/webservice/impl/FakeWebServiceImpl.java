package com.taoists.code.webservice.impl;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taoists.code.entity.CodeQuery;
import com.taoists.code.entity.FakeCode;
import com.taoists.code.entity.QueryWayStatus;
import com.taoists.code.service.CodeQueryService;
import com.taoists.code.service.FakeCodeService;
import com.taoists.code.webservice.FakeWebService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-3
 */
@Service("fakeWebServiceImpl")
@WebService(endpointInterface = "com.taoists.code.webservice.FakeWebService")
public class FakeWebServiceImpl implements FakeWebService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public int queryFakeCode(int queryWay, String fakeCode, String userNo, String userPhone) {
		logger.debug("Fake code query params [{}, {}, {}, {}]", new Object[] { queryWay, fakeCode, userNo, userPhone });

		int result;

		CodeQuery codeQuery = new CodeQuery();

		List<FakeCode> fakeCodes = fakeCodeService.findDatas("fakeCode", fakeCode);
		if (CollectionUtils.isEmpty(fakeCodes)) {
			result = -1;

		} else {

			FakeCode fake = fakeCodes.get(0);
			Integer queryCount = fake.getQueryCount();
			if (BooleanUtils.isNotTrue(fake.getStatus()) || queryCount == null) {
				result = 0;
				queryCount = 1;
				fake.setFirstQueryDateTime(DateTime.now());
			} else {
				result = 1;
				queryCount += 1;
			}

			fake.setQueryCount(queryCount);
			fake.setQueryWayStatus(QueryWayStatus.getQueryWayStatus(queryWay));
			fake.setStatus(true);
			fakeCodeService.update(fake);

			codeQuery.setFakeCode(fake);
		}

		codeQuery.setCodeNo(fakeCode);
		codeQuery.setQueryDateTime(DateTime.now());
		codeQuery.setQueryResult(result);
		codeQuery.setQueryWay(queryWay);
		codeQuery.setUserNo(userNo);
		codeQuery.setUserPhone(userPhone);

		codeQueryService.save(codeQuery);
		return result;
	}

	@Autowired
	private CodeQueryService codeQueryService;
	@Autowired
	private FakeCodeService fakeCodeService;

}
