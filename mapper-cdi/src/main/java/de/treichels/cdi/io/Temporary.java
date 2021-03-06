package de.treichels.cdi.io;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

@Qualifier
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface Temporary {
	@Nonbinding
	public String name() default "";
}
