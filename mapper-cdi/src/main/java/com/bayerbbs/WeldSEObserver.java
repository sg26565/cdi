package com.bayerbbs;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;

import com.bayerbbs.mapper.Mapper;
import com.bayerbbs.tracing.Tracing;

public class WeldSEObserver {
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

	/**
	 * Event listener called on container startup.
	 *
	 * @see org.jboss.weld.environment.se.StartMain
	 * @param event
	 */
	@Tracing
	public void run(@Observes final ContainerInitialized event) {
		logger.info("Hello Weld!");

		final String s = "3.1415";

		logger.info("Mapping {} to int: {}", s, intMapper.map(s));
		logger.info("Mapping {} to float: {}", s, floatMapper.map(s));
		logger.info("Mapping {} to double: {}", s, doubleMapper.map(s));

		logger.info("Mapping {} to boolean: {}", "false", booleanMapper.map("false"));
		logger.info("Mapping {} to boolean: {}", "0", booleanMapper.map("0"));
		logger.info("Mapping {} to boolean: {}", "", booleanMapper.map("No"));
	}
}
