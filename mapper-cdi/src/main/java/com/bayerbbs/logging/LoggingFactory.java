package com.bayerbbs.logging;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bayerbbs.tracing.Tracing;

public class LoggingFactory {
	@Produces
	@Tracing
	public Logger getLogger(final InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	}
}
