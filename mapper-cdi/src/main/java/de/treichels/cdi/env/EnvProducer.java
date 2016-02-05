package de.treichels.cdi.env;

import java.lang.reflect.Member;
import java.util.NoSuchElementException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import de.treichels.cdi.AbstractProducer;

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

		if (logger.isTraceEnabled()) {
			final Member member = ip.getMember();
			if (member == null) {
				logger.printf(Level.TRACE, "injecting Env(%s) = \"%s\"", envName, result);
			} else {
				logger.printf(Level.TRACE, "injecting Env(%s) = \"%s\" into %s.%s", envName, result, member.getDeclaringClass().getName(), member.getName());
			}
		}

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

		if (logger.isTraceEnabled()) {
			final Member member = ip.getMember();
			if (member == null) {
				logger.printf(Level.TRACE, "injecting Property(%s) = \"%s\"", propertyName, result);
			} else {
				logger.printf(Level.TRACE, "injecting Property(%s) = \"%s\" into %s.%s", propertyName, result, member.getDeclaringClass().getName(),
				        member.getName());
			}
		}

		if (result == null && annotation.strict()) {
			throw new NoSuchElementException(propertyName);
		}

		return result;
	}
}
