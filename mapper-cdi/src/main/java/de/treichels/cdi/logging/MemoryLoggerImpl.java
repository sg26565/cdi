package de.treichels.cdi.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.impl.Log4jLogEvent.Builder;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.AbstractLogger;

public class MemoryLoggerImpl extends AbstractLogger {
	private static final long serialVersionUID = 1L;
	private Level level;

	public MemoryLoggerImpl(final Class<?> clazz) {
		this(clazz.getName());
	}

	public MemoryLoggerImpl(final Class<?> clazz, final Level level) {
		this(clazz.getName(), level);
	}

	public MemoryLoggerImpl(final String name) {
		this(name, Level.ALL);
	}

	public MemoryLoggerImpl(final String name, final Level level) {
		super(name);
		this.level = level;
	}

	private boolean checkMatch(final StackTraceElement e, final Class<?> clazz) {
		if (e.getClassName().equals(clazz.getName())) {
			// same class
			return true;
		}

		final Class<?> superclass = clazz.getSuperclass();
		if (superclass == null) {
			// match - not this class or any parent
			return false;
		}

		// check element against parent class
		return checkMatch(e, superclass);
	}

	private StackTraceElement getCaller() {
		boolean first = true;

		// move the call stack upwards and find the first element that does not
		// belong to this class or any parent class
		for (final StackTraceElement e : Thread.currentThread().getStackTrace()) {
			if (first) {
				first = false;
				continue;
			}

			if (checkMatch(e, this.getClass())) {
				continue;
			}

			return e;
		}

		return null;
	}

	@Override
	public Level getLevel() {
		return level;
	}

	@Override
	public boolean isEnabled(final Level level) {
		return this.level.isLessSpecificThan(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final CharSequence message, final Throwable t) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final Message message, final Throwable t) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final Object message, final Throwable t) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object... params) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2, final Object param3) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2, final Object param3,
			final Object param4) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2, final Object param3,
			final Object param4, final Object param5) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2, final Object param3,
			final Object param4, final Object param5, final Object param6) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2, final Object param3,
			final Object param4, final Object param5, final Object param6, final Object param7) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2, final Object param3,
			final Object param4, final Object param5, final Object param6, final Object param7, final Object param8) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2, final Object param3,
			final Object param4, final Object param5, final Object param6, final Object param7, final Object param8, final Object param9) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Object param1, final Object param2, final Object param3,
			final Object param4, final Object param5, final Object param6, final Object param7, final Object param8, final Object param9,
			final Object param10) {
		return isEnabled(level);
	}

	@Override
	public boolean isEnabled(final Level level, final Marker marker, final String message, final Throwable t) {
		return isEnabled(level);
	}

	@Override
	public void logMessage(final String loggerFQCN, final Level level, final Marker marker, final Message message, final Throwable t) {
		final Builder builder = new Log4jLogEvent.Builder();

		builder.setIncludeLocation(true).setLevel(level).setLoggerFqcn(getClass().getName()).setLoggerName(getName()).setMarker(marker).setMessage(message)
				.setSource(getCaller()).setThrown(t);

		MemoryLoggerFactory.addLogEvent(builder.build());
	}

	public void setLevel(final Level level) {
		this.level = level;
	}
}
