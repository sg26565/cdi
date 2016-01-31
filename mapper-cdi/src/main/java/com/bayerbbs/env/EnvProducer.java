package com.bayerbbs.env;

import java.util.NoSuchElementException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.slf4j.Logger;

@ApplicationScoped
public class EnvProducer {
	@Inject
	private Logger logger;

	@Produces
	@SystemProperty("")
	public String getSystemProperty(final InjectionPoint ip) {
		final String propertyName = ip.getAnnotated().getAnnotation(SystemProperty.class).value();
		final String result = System.getProperty(propertyName);
		logger.trace("{} = {}", propertyName, result);

		if (result == null) {
			throw new NoSuchElementException(propertyName);
		}

		return result;
	}

	@Produces
	@Environment("")
	public String getEnvironment(final InjectionPoint ip) {
		final String envName = ip.getAnnotated().getAnnotation(Environment.class).value();
		final String result = System.getenv(envName);
		logger.trace("{} = {}", envName, result);

		if (result == null) {
			throw new NoSuchElementException(envName);
		}

		return result;
	}
}
