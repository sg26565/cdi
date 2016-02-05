package de.treichels.cdi.io;

import java.io.File;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import de.treichels.cdi.AbstractProducer;

@ApplicationScoped
public class FileProducer extends AbstractProducer {
	@Inject
	private Logger logger;

	@Produces
	@Temporary
	public File getTempFile(final InjectionPoint ip) throws IOException {
		final Temporary annotation = getAnnotation(ip, Temporary.class);
		final String fileName = annotation.name();
		final File file;

		if (fileName != null && fileName.length() > 1) {
			file = File.createTempFile(fileName, null);
		} else {
			file = File.createTempFile(ip.getMember().getDeclaringClass().getSimpleName(), null);
		}

		if (logger.isTraceEnabled()) {
			logger.printf(Level.TRACE, "injecting file \"%s\" into %s.%s", file, ip.getMember().getDeclaringClass().getName(), ip.getMember().getName());
		}

		return file;
	}
}
