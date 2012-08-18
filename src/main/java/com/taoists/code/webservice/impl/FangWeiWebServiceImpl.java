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
import com.taoists.code.entity.FangWeiCode;
import com.taoists.code.entity.enums.CodeIssueTypeEnum;
import com.taoists.code.entity.enums.QueryWayStatus;
import com.taoists.code.service.CodeQueryService;
import com.taoists.code.service.FangWeiCodeService;
import com.taoists.code.webservice.FangWeiWebService;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-18
 */
@Service("fangWeiWebServiceImpl")
@WebService(endpointInterface = "com.taoists.code.webservice.FangWeiWebService")
public class FangWeiWebServiceImpl implements FangWeiWebService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public int queryFangWeiCode(int queryWay, String code, String userNo, String userPhone) {
		logger.debug("Fang wei code query params [{}, {}, {}, {}]", new Object[] { queryWay, code, userNo, userPhone });
		return queryCodeWeiCode(queryWay, code, userNo, userPhone, CodeIssueTypeEnum.fangWeiCode.value());
	}

	@Override
	public int queryStickCode(int queryWay, String code, String userNo, String userPhone) {
		logger.debug("Stick code query params [{}, {}, {}, {}]", new Object[] { queryWay, code, userNo, userPhone });
		return queryCodeWeiCode(queryWay, code, userNo, userPhone, CodeIssueTypeEnum.stick.value());
	}

	public int queryCodeWeiCode(int queryWay, String code, String userNo, String userPhone, String codeType) {
		int result;

		CodeQuery codeQuery = new CodeQuery();

		List<FangWeiCode> fangWeis = fangWeiCodeService.findCodes(code, codeType);
		if (CollectionUtils.isEmpty(fangWeis)) {
			result = -1;

		} else {
			FangWeiCode fangWei = fangWeis.get(0);
			Integer queryCount = fangWei.getQueryCount();
			if (BooleanUtils.isNotTrue(fangWei.getStatus()) || queryCount == null) {
				result = 0;
				queryCount = 1;
				fangWei.setFirstQueryDateTime(DateTime.now());
			} else {
				result = 1;
				queryCount += 1;
			}

			fangWei.setQueryCount(queryCount);
			fangWei.setQueryWayStatus(QueryWayStatus.getQueryWayStatus(queryWay).getCode());
			fangWei.setStatus(true);
			fangWeiCodeService.update(fangWei);

			codeQuery.setFangWeiCode(fangWei);
		}

		codeQuery.setCodeNo(code);
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
	private FangWeiCodeService fangWeiCodeService;

}
