package de.treichels.cdi;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.spi.InjectionPoint;

public class AbstractProducer {
	@SuppressWarnings("unchecked")
	protected <T extends Annotation> T getAnnotation(final InjectionPoint ip, final Class<T> annotationClass) {
		T result = null;

		if (ip.getAnnotated() != null) {
			// retrieve annotation from source code
			result = ip.getAnnotated().getAnnotation(annotationClass);
		}

		if (result == null) {
			// programmatic lookup - filter through qualifiers
			result = (T) ip.getQualifiers().stream().filter(a -> a.annotationType().equals(annotationClass)).findFirst().get();
		}

		return result;
	}
}