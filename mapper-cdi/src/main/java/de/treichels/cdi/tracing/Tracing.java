package com.bayerbbs.tracing;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Target({ METHOD, TYPE })
@Retention(RUNTIME)
public @interface Tracing {
	public enum Level {
		TRACE, DEBUG, INFO, WARN, ERROR
	}

	@Nonbinding
	public Level level() default Level.TRACE;
}
