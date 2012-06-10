package com.taoists.common.util;

import java.util.List;
import java.util.StringTokenizer;

import com.google.common.collect.Lists;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public class StringUtils {

	public static List<String> stringTokenizer(String value) {
		List<String> results = Lists.newArrayList();
		StringTokenizer st = new StringTokenizer(value);
		while (st.hasMoreElements()) {
			String str = st.nextElement().toString();
			if (org.apache.commons.lang.StringUtils.isNotBlank(str)) {
				results.add(str);
			}
		}
		return results;
	}

}
