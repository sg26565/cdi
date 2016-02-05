package com.bayerbbs;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.spi.InjectionPoint;

public class AbstractProducer {
	@SuppressWarnings("unchecked")
	protected <T extends Annotation> T getAnnotation(final InjectionPoint ip, final Class<T> annotationClass) {
		if (ip.getAnnotated() != null) {
			// retrieve annotation from source code
			return ip.getAnnotated().getAnnotation(annotationClass);
		} else {
			// programmatic lookup - filter through qualifiers
			return (T) ip.getQualifiers().stream().filter(a -> a.annotationType().equals(annotationClass)).findFirst().get();
		}
	}
}