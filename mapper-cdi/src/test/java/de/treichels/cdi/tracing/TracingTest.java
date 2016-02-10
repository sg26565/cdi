package de.treichels.cdi.tracing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Queue;

import javax.inject.Inject;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.treichels.cdi.logging.MemoryLoggerFactory;
import de.treichels.cdi.testing.CdiTestRunner;

@RunWith(CdiTestRunner.class)
@Tracing
public class TracingTest {
	@Inject
	private Logger logger;

	@Inject
	private Foo foo;

	@Test
	public void testTracing() {
		logger.debug("log4j logger");
		foo.foo(7);

		final LoggerContextFactory factory = LogManager.getFactory();
		try {
			LogManager.setFactory(MemoryLoggerFactory.getInstance());

			final int result = foo.foo(7);
			assertEquals(105, result);
		} finally {
			LogManager.setFactory(factory);
		}

		final Queue<LogEvent> events = MemoryLoggerFactory.getLogEvents();
		assertNotNull(events);
		assertEquals(4, events.size());

		final String pattern = "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n";
		final PatternLayout layout = PatternLayout.newBuilder().withPattern(pattern).build();
		logger.debug("memory logger");
		events.stream().map(e -> layout.toSerializable(e)).forEach(System.out::print);

		LogEvent event = events.remove();
		assertEquals(Level.TRACE, event.getLevel());
		assertEquals(Foo.class.getName(), event.getLoggerName());
		assertEquals("method entry foo(7)", event.getMessage().getFormattedMessage());

		event = events.remove();
		assertEquals(Level.TRACE, event.getLevel());
		assertEquals(Bar.class.getName(), event.getLoggerName());
		assertEquals("method entry bar(21)", event.getMessage().getFormattedMessage());

		event = events.remove();
		assertEquals(Level.TRACE, event.getLevel());
		assertEquals(Bar.class.getName(), event.getLoggerName());
		assertEquals("method result bar(21): 105", event.getMessage().getFormattedMessage());

		event = events.remove();
		assertEquals(Level.TRACE, event.getLevel());
		assertEquals(Foo.class.getName(), event.getLoggerName());
		assertEquals("method result foo(7): 105", event.getMessage().getFormattedMessage());
	}
}
