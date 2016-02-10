package de.treichels.cdi.logging;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

public class MemoryLoggerFactory implements LoggerContextFactory, LoggerContext {
	private static final Queue<LogEvent> LOG_EVENTS = new LinkedList<>();
	private static final MemoryLoggerFactory INSTANCE = new MemoryLoggerFactory();

	public static void addLogEvent(final LogEvent event) {
		LOG_EVENTS.add(event);
	}

	public static MemoryLoggerFactory getInstance() {
		return INSTANCE;
	}

	public static Queue<LogEvent> getLogEvents() {
		return LOG_EVENTS;
	}

	private final Map<String, MemoryLoggerImpl> loggers = new HashMap<>();

	@Override
	public LoggerContext getContext(final String fqcn, final ClassLoader loader, final Object externalContext, final boolean currentContext) {
		return this;
	}

	@Override
	public LoggerContext getContext(final String fqcn, final ClassLoader loader, final Object externalContext, final boolean currentContext,
	        final URI configLocation, final String name) {
		return this;
	}

	@Override
	public Object getExternalContext() {
		return null;
	}

	@Override
	public ExtendedLogger getLogger(final String name) {
		final MemoryLoggerImpl logger;

		if (hasLogger(name)) {
			logger = loggers.get(name);
		} else {
			logger = new MemoryLoggerImpl(name);
			loggers.put(name, logger);
		}

		return logger;
	}

	@Override
	public ExtendedLogger getLogger(final String name, final MessageFactory messageFactory) {
		return getLogger(name);
	}

	@Override
	public boolean hasLogger(final String name) {
		return loggers.containsKey(name);
	}

	@Override
	public boolean hasLogger(final String name, final Class<? extends MessageFactory> messageFactoryClass) {
		return hasLogger(name);
	}

	@Override
	public boolean hasLogger(final String name, final MessageFactory messageFactory) {
		return hasLogger(name);
	}

	@Override
	public void removeContext(final LoggerContext context) {
	}
}
