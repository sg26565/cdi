package de.treichels.cdi.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Queue;

import javax.inject.Inject;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.spi.StandardLevel;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.treichels.cdi.testing.CdiTestRunner;
import de.treichels.cdi.tracing.Tracing;

@RunWith(CdiTestRunner.class)
@Tracing
public class LoggerProducerTest {
	@Inject
	@MemoryLogger(level = StandardLevel.DEBUG)
	private Logger memoryLogger;

	@Inject
	private Logger normalLogger;

	@Test
	public void testMemoryLogger() throws InterruptedException {
		assertNotNull(memoryLogger);
		assertEquals(MemoryLoggerImpl.class, memoryLogger.getClass());
		assertEquals(LoggerProducerTest.class.getName(), memoryLogger.getName());
		assertEquals(Level.DEBUG, memoryLogger.getLevel());

		final String message = "test message";
		final Marker marker = MarkerManager.getMarker("test");
		final Throwable t = new RuntimeException("42");

		final long ts1 = System.currentTimeMillis();
		Thread.sleep(1);
		memoryLogger.info(marker, message, t);
		memoryLogger.debug("test {} {}", "one", "two");
		memoryLogger.trace("this will not be logged");
		Thread.sleep(1);
		final long ts2 = System.currentTimeMillis();

		final Queue<LogEvent> events = MemoryLoggerFactory.getLogEvents();
		assertEquals(2, events.size());

		final LogEvent event1 = events.remove();
		assertEquals(Level.INFO, event1.getLevel());
		assertEquals(marker, event1.getMarker());
		assertEquals(message, event1.getMessage().getFormattedMessage());
		assertEquals(t, event1.getThrown());
		assertTrue(ts1 < event1.getTimeMillis());
		assertTrue(ts2 > event1.getTimeMillis());

		final LogEvent event2 = events.remove();
		assertEquals(Level.DEBUG, event2.getLevel());
		assertNull(event2.getMarker());
		assertEquals("test one two", event2.getMessage().getFormattedMessage());
		assertNull(event2.getThrown());
		assertTrue(ts1 < event2.getTimeMillis());
		assertTrue(ts2 > event2.getTimeMillis());
	}

	@Test
	public void testNomalLogger() {
		assertNotNull(normalLogger);
		assertNotEquals(MemoryLoggerImpl.class, normalLogger.getClass());
		assertEquals(LoggerProducerTest.class.getName(), normalLogger.getName());
	}
}
