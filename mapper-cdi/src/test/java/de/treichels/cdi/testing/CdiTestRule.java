package de.treichels.cdi.testing;

import javax.inject.Inject;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CdiTestRule implements TestRule {
	@Inject
	Logger logger;

	@Override
	public Statement apply(final Statement base, final Description description) {
		logger.printf(Level.TRACE, "invoking method \"%s\" on class \"%s\"", description.getMethodName(), description.getClassName());
		return base;
	}
}
