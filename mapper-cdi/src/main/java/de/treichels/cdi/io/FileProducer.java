package com.bayerbbs.io;

import java.io.File;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.bayerbbs.AbstractProducer;

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

		logger.trace("fileNAme = {}", file);

		return file;
	}
}
