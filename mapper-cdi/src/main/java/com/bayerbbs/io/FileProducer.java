package com.bayerbbs.io;

import java.io.File;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.slf4j.Logger;

@ApplicationScoped
public class FileProducer {
	@Inject
	private Logger logger;

	@Produces
	@Temporary
	public File getTempFile(final InjectionPoint ip) throws IOException {
		final String fileName = ip.getAnnotated().getAnnotation(Temporary.class).name();
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
