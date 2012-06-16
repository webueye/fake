package com.taoists.common.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public class DateUtils {

	public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static DateTime toDateTime(String dateTime) {
		if(StringUtils.isBlank(dateTime)){
			return null;
		}
		return DateTimeFormat.forPattern(PATTERN).parseDateTime(dateTime);
	}
	
	public static String toString(String dateTime, String pattern) {
		return new DateTime(dateTime).toString(pattern);
	}
	
	public static String todayToString() {
		return new LocalDate().toString("yyMMdd");
	}

}
