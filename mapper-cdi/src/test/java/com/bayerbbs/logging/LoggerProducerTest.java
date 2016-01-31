package com.bayerbbs.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.bayerbbs.logging.memory.AbstractLoggerBase.Level;
import com.bayerbbs.logging.memory.LogEntry;
import com.bayerbbs.logging.memory.MemoryLogger;
import com.bayerbbs.testing.CdiTestRunner;

@RunWith(CdiTestRunner.class)
public class LoggerProducerTest {
	@Inject
	Logger logger;

	@Test
	public void test() {
		assertNotNull(logger);
		assertEquals(MemoryLogger.class, logger.getClass());
		assertEquals(LoggerProducerTest.class.getName(), logger.getName());

		logger.info("test message");

		final MemoryLogger memoryLogger = (MemoryLogger) logger;
		assertEquals(1, memoryLogger.size());
		final LogEntry entry = memoryLogger.remove();

		assertEquals(Level.INFO, entry.getLevel());
		assertEquals("test message", entry.getMessage().getMessage());
	}
}
