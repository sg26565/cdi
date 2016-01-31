package com.bayerbbs.logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProducer {
	@Produces
	public Logger getLogger(final InjectionPoint ip) {
		final String loggerName = ip.getMember().getDeclaringClass().getName();
		LoggerFactory.getLogger(LoggerProducer.class).trace("logger = {}", loggerName);
		return LoggerFactory.getLogger(loggerName);
	}
}
