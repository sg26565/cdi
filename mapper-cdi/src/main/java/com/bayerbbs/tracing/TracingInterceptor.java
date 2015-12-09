package com.bayerbbs.tracing;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bayerbbs.tracing.Tracing.Level;

@Tracing
@Interceptor
public class TracingInterceptor {
	private Logger logger;
	private Level level;

	private void log(final String format, final Object... args) {
		switch (level) {
		case DEBUG:
			logger.debug(format, args);
			break;
		case ERROR:
			logger.error(format, args);
			break;
		case INFO:
			logger.info(format, args);
			break;
		case TRACE:
			logger.trace(format, args);
			break;
		case WARN:
			logger.warn(format, args);
			break;
		default:
			break;
		}
	}

	@AroundInvoke
	public Object traceMethodCall(final InvocationContext ctx) throws Exception {
		final Method method = ctx.getMethod();
		final String methodName = method.getName();
		final Class<?> returnType = method.getReturnType();
		final Object[] parameters = ctx.getParameters();
		logger = LoggerFactory.getLogger(method.getDeclaringClass());
		level = method.getAnnotation(Tracing.class).level();

		log("Method entry: {}({})", methodName, parameters);

		final Object result = ctx.proceed();

		if (returnType == Void.class) {
			log("Method exit: {}({})", methodName, parameters);
		} else {
			log("Method exit: {}({}) => {}", methodName, parameters, result);
		}

		return result;
	}
}
