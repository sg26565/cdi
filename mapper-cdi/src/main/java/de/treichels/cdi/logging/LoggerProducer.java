package de.treichels.cdi.logging;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerProducer {
	private final Logger logger = LogManager.getLogger();

	@Produces
	public Logger getLog4JLogger(final InjectionPoint ip) {
		final Class<?> declaringClass = ip.getMember().getDeclaringClass();
		logger.printf(Level.TRACE, "injecting log4j logger into %s.%s", declaringClass.getName(), ip.getMember().getName());
		return LogManager.getLogger(declaringClass);
	}

	@Produces
	@Memory
	public Logger getMemoryLogger(final InjectionPoint ip) {
		final Class<?> declaringClass = ip.getMember().getDeclaringClass();
		logger.printf(Level.TRACE, "injecting memory logger into %s.%s", declaringClass.getName(), ip.getMember().getName());
		return MemoryLoggerFactory.getInstance().getLogger(declaringClass.getName());
	}
}
