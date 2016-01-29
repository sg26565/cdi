package com.bayerbbs.logging;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFactory {
	private final Map<Class<?>, Logger> loggers = new HashMap<Class<?>, Logger>();

	@Produces
	@Alternative
	public Logger getMemoryLogger(final InjectionPoint ip) {
		final Class<?> clazz = ip.getMember().getDeclaringClass();
		Logger result = loggers.get(clazz);

		if (result == null) {
			result = new MemoryLogger(clazz);
			loggers.put(clazz, result);
		}

		return result;
	}

	@Produces
	public Logger getStandardLogger(final InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	}
}
