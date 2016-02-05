package com.bayerbbs.env;

import java.util.NoSuchElementException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.bayerbbs.AbstractProducer;

@ApplicationScoped
public class EnvProducer extends AbstractProducer {
	@Inject
	private Logger logger;

	@Produces
	@Environment("")
	public String getEnvironment(final InjectionPoint ip) {
		final Environment annotation = getAnnotation(ip, Environment.class);

		final String envName = annotation.value();
		final String result = System.getenv(envName);
		logger.trace("{} = {}", envName, result);

		if (result == null && annotation.strict()) {
			throw new NoSuchElementException(envName);
		}

		return result;
	}

	@Produces
	@SystemProperty("")
	public String getSystemProperty(final InjectionPoint ip) {
		final SystemProperty annotation = getAnnotation(ip, SystemProperty.class);

		final String propertyName = annotation.value();
		final String result = System.getProperty(propertyName);
		logger.trace("{} = {}", propertyName, result);

		if (result == null && annotation.strict()) {
			throw new NoSuchElementException(propertyName);
		}

		return result;
	}
}
