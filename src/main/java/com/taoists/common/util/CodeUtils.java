package com.taoists.common.util;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-3
 */
public class CodeUtils {
	
	public static String genCode(int length){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++){
			sb.append(RandomUtils.nextInt(10));
		}
		return sb.toString();
	}

}
