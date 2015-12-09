package com.bayerbbs.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

@RunWith(CdiRunner.class)
@AdditionalClasses(LoggingFactory.class)
public class LoggingFactoryTest {
	@Inject Logger logger;
	
	@Test
	public void test() {
		assertNotNull(logger);
		assertEquals(LoggingFactoryTest.class.getName(),logger.getName());
		logger.info("test successful");
	}
}
