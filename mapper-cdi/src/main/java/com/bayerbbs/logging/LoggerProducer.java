package com.bayerbbs.logging;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bayerbbs.logging.memory.MemoryLogger;

public class LoggerProducer {
	private final Map<Class<?>, Logger> loggers = new HashMap<Class<?>, Logger>();

	@Produces
	public org.apache.logging.log4j.Logger getLog4JLogger(final InjectionPoint ip) {
		final String loggerName = ip.getMember().getDeclaringClass().getName();
		LogManager.getLogger(LoggerProducer.class).trace("logger = {}", loggerName);
		return LogManager.getLogger(loggerName);
	}

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
	public Logger getSLF4JLogger(final InjectionPoint ip) {
		final String loggerName = ip.getMember().getDeclaringClass().getName();
		LoggerFactory.getLogger(LoggerProducer.class).trace("logger = {}", loggerName);
		return LoggerFactory.getLogger(loggerName);
	}
}
