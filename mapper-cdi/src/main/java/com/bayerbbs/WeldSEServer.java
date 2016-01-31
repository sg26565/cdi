package com.bayerbbs;

import java.io.File;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;

import com.bayerbbs.env.Environment;
import com.bayerbbs.env.SystemProperty;
import com.bayerbbs.io.Temporary;
import com.bayerbbs.mapper.Mapper;
import com.bayerbbs.tracing.Tracing;
import com.bayerbbs.tracing.Tracing.Level;

public class WeldSEServer {
	@Inject
	private Mapper<String, Integer> intMapper;

	@Inject
	private Mapper<String, Float> floatMapper;

	@Inject
	private Mapper<String, Double> doubleMapper;

	@Inject
	private Mapper<String, Boolean> booleanMapper;

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
	@Tracing(level = Level.INFO)
	public void run(@Observes final ContainerInitialized event) {
		logger.info("Hello Weld!");

		logger.info("tempDir = {}", tempDir);
		logger.info("homeShare = {}", homeShare);

		logger.info("f1 = {}", f1.getAbsolutePath());
		logger.info("f2 = {}", f2.getAbsolutePath());

		final String s = "3.1415";

		logger.info("String \"{}\" to int: {}", s, intMapper.map(s));
		logger.info("String \"{}\" to float: {}", s, floatMapper.map(s));
		logger.info("String \"{}\" to double: {}", s, doubleMapper.map(s));

		logger.info("String \"{}\" to boolean: {}", "false", booleanMapper.map("false"));
		logger.info("String \"{}\" to boolean: {}", "0", booleanMapper.map("0"));
		logger.info("String \"{}\" to boolean: {}", "No", booleanMapper.map("No"));
	}
}
