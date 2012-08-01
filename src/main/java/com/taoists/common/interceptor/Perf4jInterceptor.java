package com.taoists.common.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.google.common.collect.Maps;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-31
 */
public class Perf4jInterceptor implements MethodBeforeAdvice, AfterReturningAdvice {

	private Map<String, StopWatch> watches = Maps.newHashMap();

	public void before(Method method, Object[] args, Object target) throws Throwable {
		String tag = getTag(target, method);
		StopWatch stopWatch;
		if (watches.containsKey(tag)) {
			stopWatch = watches.get(tag);
			stopWatch.start();
		} else {
			stopWatch = new Slf4JStopWatch(tag, Arrays.toString(args));
			watches.put(tag, stopWatch);
		}
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		String tag = getTag(target, method);
		if (watches.containsKey(tag)) {
			StopWatch stopWatch = watches.get(tag);
			stopWatch.stop();
		}
	}

	private String getTag(Object target, Method method) {
		String className = "";
		if (target != null) {
			className = target.toString();
			int loc = className.indexOf("@");
			if (loc >= 0) {
				className = className.substring(className.lastIndexOf(".") + 1, loc);
			}
		}
		return className + "." + method.getName();
	}

}