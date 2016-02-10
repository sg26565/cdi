package de.treichels.cdi.tracing;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

import org.apache.logging.log4j.spi.StandardLevel;

@InterceptorBinding
@Target({ METHOD, TYPE, PACKAGE })
@Retention(RUNTIME)
public @interface Tracing {
	@Nonbinding
	public StandardLevel level() default StandardLevel.TRACE;
}
