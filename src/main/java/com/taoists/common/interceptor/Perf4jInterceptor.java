package com.taoists.common.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.google.common.collect.Maps;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-31
 */
public class Perf4jInterceptor implements MethodInterceptor, MethodBeforeAdvice, AfterReturningAdvice {

	private Map<String, StopWatch> watches = Maps.newHashMap();

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String completeMethodName = getCompleteMethodName(invocation.getThis(), invocation.getMethod());
		StopWatch stopWatch = new Slf4JStopWatch(completeMethodName, Arrays.toString(invocation.getArguments()));
		stopWatch.start();
		Object obj = invocation.proceed();
		stopWatch.stop();
		return obj;
	}

	public void before(Method method, Object[] args, Object target) throws Throwable {
		String completeMethodName = getCompleteMethodName(target, method);
		// 创建性能日志记录器
		StopWatch stopWatch;
		if (watches.containsKey(completeMethodName)) {
			stopWatch = watches.get(completeMethodName);
			stopWatch.start();
		} else {
			stopWatch = new Slf4JStopWatch(completeMethodName, Arrays.toString(args));
			watches.put(completeMethodName, stopWatch);
		}
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		String completeMethodName = getCompleteMethodName(target, method);
		// 记录性能
		if (watches.containsKey(completeMethodName)) {
			StopWatch stopWatch = watches.get(completeMethodName);
			stopWatch.stop();
		}
	}

	private String getCompleteMethodName(Object target, Method method) {
		String className = "";
		if (target != null) {
			className = target.toString();
			int loc = className.indexOf("@");
			if (loc >= 0) {
				className = className.substring(className.lastIndexOf(".") + 1, loc);
			}
		}
		return className + "_" + method.getName();
	}

}