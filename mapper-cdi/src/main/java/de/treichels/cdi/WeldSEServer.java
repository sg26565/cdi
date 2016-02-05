package de.treichels.cdi;

import java.io.File;
import java.util.function.Function;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import de.treichels.cdi.env.Environment;
import de.treichels.cdi.env.SystemProperty;
import de.treichels.cdi.io.Temporary;
import de.treichels.cdi.tracing.Tracing;

@Tracing
public class WeldSEServer {
	@Inject
	private Function<String, Integer> intMapper;

	@Inject
	private Function<String, Float> floatMapper;

	@Inject
	private Function<String, Double> doubleMapper;

	@Inject
	private Function<String, Boolean> booleanMapper;

	@Inject
	private Logger logger;

	@Inject
	@SystemProperty("java.io.tmpdir")
	private String tempDir;

	@Inject
	@Environment("homeshare")
	private String homeShare;

	@Inject
	@Temporary(name = "hallo")
	private File f1;

	@Inject
	@Temporary
	private File f2;

	/**
	 * Event listener called on container startup.
	 *
	 * @see org.jboss.weld.environment.se.StartMain
	 * @param event
	 */
	public void run(@Observes final ContainerInitialized event) {
		logger.info("Hello Weld!");

		logger.info("tempDir = {}", tempDir);
		logger.info("homeShare = {}", homeShare);

		logger.info("f1 = {}", f1.getAbsolutePath());
		logger.info("f2 = {}", f2.getAbsolutePath());

		final String s = "3.1415";

		logger.info("String \"{}\" to int: {}", s, intMapper.apply(s));
		logger.info("String \"{}\" to float: {}", s, floatMapper.apply(s));
		logger.info("String \"{}\" to double: {}", s, doubleMapper.apply(s));

		logger.info("String \"{}\" to boolean: {}", "false", booleanMapper.apply("false"));
		logger.info("String \"{}\" to boolean: {}", "0", booleanMapper.apply("0"));
		logger.info("String \"{}\" to boolean: {}", "No", booleanMapper.apply("No"));
	}
}
