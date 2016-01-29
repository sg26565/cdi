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

	private boolean isTracingEnabled() {
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
		final Tracing annotation = method.getAnnotation(Tracing.class);
		logger = LoggerFactory.getLogger(method.getDeclaringClass());
		level = annotation == null ? Level.TRACE : annotation.level();
		final Object result;

		if (isTracingEnabled()) {
			final String methodName = method.getName();
			final Object[] parameters = ctx.getParameters();
			final Class<?> returnType = method.getReturnType();
			String returnTypeName = returnType.getTypeName();

			log("Method entry: {} {}({})", returnTypeName, methodName, parameters);

			result = ctx.proceed();

			if (returnType.equals(Void.TYPE)) {
				log("Method exit: void {}({})", methodName, parameters);
			} else {
				log("Method exit: {} {}({}) => {}", returnTypeName, methodName, parameters, result);
			}
		} else {
			result = ctx.proceed();
		}

		return result;
	}
}
