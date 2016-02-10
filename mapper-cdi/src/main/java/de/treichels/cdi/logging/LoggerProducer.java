package de.treichels.cdi.logging;

import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;

import de.treichels.cdi.AbstractProducer;

@ApplicationScoped
public class LoggerProducer extends AbstractProducer {
	private static final Logger logger = LogManager.getLogger();

	@Inject
	private Function<StandardLevel, Level> mapper;

	@Produces
	public Logger getLog4JLogger(final InjectionPoint ip) {
		final Class<?> declaringClass = ip.getMember().getDeclaringClass();
		logger.printf(Level.TRACE, "injecting log4j logger into %s.%s", declaringClass.getName(), ip.getMember().getName());
		return LogManager.getLogger(declaringClass);
	}

	@Produces
	@MemoryLogger
	public Logger getMemoryLogger(final InjectionPoint ip) {
		final Class<?> declaringClass = ip.getMember().getDeclaringClass();
		logger.printf(Level.TRACE, "injecting memory logger into %s.%s", declaringClass.getName(), ip.getMember().getName());
		final MemoryLoggerImpl logger = (MemoryLoggerImpl) MemoryLoggerFactory.getInstance().getLogger(declaringClass.getName());

		final MemoryLogger annotation = getAnnotation(ip, MemoryLogger.class);
		logger.setLevel(annotation == null ? Level.ALL : mapper.apply(annotation.level()));

		return logger;
	}
}
