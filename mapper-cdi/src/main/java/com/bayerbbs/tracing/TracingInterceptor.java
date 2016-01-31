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
	private void log(final Logger logger, final Level level, final String message, final Object... args) {
		switch (level) {
		case DEBUG:
			logger.debug(message, args);
			break;
		case ERROR:
			logger.error(message, args);
			break;
		case INFO:
			logger.info(message, args);
			break;
		case TRACE:
			logger.trace(message, args);
			break;
		case WARN:
			logger.warn(message, args);
			break;
		}
	}

	private boolean isEnabled(final Logger logger, final Level level) {
		switch (level) {
		case DEBUG:
			return logger.isDebugEnabled();
		case ERROR:
			return logger.isErrorEnabled();
		case INFO:
			return logger.isInfoEnabled();
		case TRACE:
			return logger.isTraceEnabled();
		case WARN:
			return logger.isWarnEnabled();
		default:
			return false;
		}
	}

	@AroundInvoke
	public Object traceMethodCall(final InvocationContext ctx) throws Exception {
		final Method method = ctx.getMethod();
		final Logger logger = LoggerFactory.getLogger(method.getDeclaringClass());
		final Tracing annotation = method.getAnnotation(Tracing.class);
		final Level level = annotation == null ? Level.TRACE : annotation.level();

		final Object result;

		if (isEnabled(logger, level)) {
			final String methodName = method.getName();
			final Object[] parameters = ctx.getParameters();
			final Class<?> returnType = method.getReturnType();

			log(logger, level, "{}({}) entry", methodName, parameters);

			result = ctx.proceed();

			if (returnType.equals(Void.TYPE)) {
				log(logger, level, "{}({}) exit", methodName, parameters);
			} else {
				log(logger, level, "{}({}) result: {}", methodName, parameters, result);
			}
		} else {
			result = ctx.proceed();
		}

		return result;
	}
}
