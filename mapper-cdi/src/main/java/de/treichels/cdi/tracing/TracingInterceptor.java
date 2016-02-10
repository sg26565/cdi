package de.treichels.cdi.tracing;

import static java.util.stream.Collectors.joining;

import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;

@Tracing
@Interceptor
public class TracingInterceptor {
	@Inject
	private Function<StandardLevel, Level> mapper;

	@AroundInvoke
	public Object traceMethodCall(final InvocationContext ctx) throws Exception {
		final Method method = ctx.getMethod();
		final Logger logger = LogManager.getLogger(method.getDeclaringClass());

		// method annotation
		Tracing annotation = method.getAnnotation(Tracing.class);

		if (annotation == null) {
			// type annotation
			annotation = method.getDeclaringClass().getAnnotation(Tracing.class);
		}

		if (annotation == null) {
			// package annotation
			annotation = method.getDeclaringClass().getPackage().getAnnotation(Tracing.class);
		}

		final Level level = annotation == null ? Level.TRACE : mapper.apply(annotation.level());
		final Object result;

		if (logger.isEnabled(level)) {
			final String methodName = method.getName();
			final Object[] parameters = ctx.getParameters();
			final Class<?> returnType = method.getReturnType();

			final String paramString = Stream.of(parameters).map(o -> o.toString()).collect(joining(", "));

			logger.printf(level, "method entry %s(%s)", methodName, paramString);

			result = ctx.proceed();

			if (returnType.equals(Void.TYPE)) {
				logger.printf(level, "method exit %s(%s)", methodName, paramString);
			} else {
				logger.printf(level, "method result %s(%s): %s", methodName, paramString, result);
			}
		} else {
			result = ctx.proceed();
		}

		return result;
	}
}
