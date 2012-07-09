package com.taoists.common.util;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.LocalDate;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public class CodeUtils {

	public static final Integer BATCH_LENGTH = 3;

	public static String genCode(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(RandomUtils.nextInt(10));
		}
		return sb.toString();
	}

	public static String getYearLastBit() {
		return LocalDate.now().toString("yyyy").substring(3, 4);
	}

	public static String getCodePrefix(Long batchNum) {
		String batchNumStr = batchNum.toString();
		return getYearLastBit() + fillZero(batchNumStr, BATCH_LENGTH);
	}

	public static String fillZero(String str, int length) {
		if (str.length() < length) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length - str.length(); i++) {
				sb.append("0");
			}
			sb.append(str);
			return sb.toString();
		}
		return str;
	}

}
