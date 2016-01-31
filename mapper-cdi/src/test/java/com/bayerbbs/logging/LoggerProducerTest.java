package com.bayerbbs.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.bayerbbs.testing.CdiTestRunner;

@RunWith(CdiTestRunner.class)
public class LoggingFactoryTest {
	@Inject Logger logger;
	
	@Test
	public void test() {
		assertNotNull(logger);
		assertEquals(LoggingFactoryTest.class.getName(),logger.getName());
		logger.info("test successful");
	}
}
