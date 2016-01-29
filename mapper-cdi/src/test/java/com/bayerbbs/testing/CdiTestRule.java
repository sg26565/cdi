package com.bayerbbs.testing;

import javax.inject.Inject;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;

public class CdiTestRule implements TestRule {
	@Inject
	Logger logger;
	
	@Override
	public Statement apply(Statement base, Description description) {
		logger.debug(description.toString());
		return base;
	}
}
