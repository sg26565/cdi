package com.bayerbbs.logging.memory;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

abstract public class AbstractLoggerBase extends MarkerIgnoringBase {
	public enum Level {
		DEBUG, ERROR, INFO, TRACE, WARN
	}

	private static final long serialVersionUID = 1L;;

	private boolean debugEnabled = false;
	private boolean errorEnabled = false;
	private boolean infoEnabled = false;
	private boolean traceEnabled = false;
	private boolean warnEnabled = false;

	@Override
	public void debug(final String msg) {
		log(Level.DEBUG, msg);
	}

	@Override
	public void debug(final String format, final Object arg) {
		log(Level.DEBUG, format, arg);
	}

	@Override
	public void debug(final String format, final Object... arguments) {
		log(Level.DEBUG, format, arguments);
	}

	@Override
	public void debug(final String format, final Object arg1, final Object arg2) {
		log(Level.DEBUG, format, arg1, arg2);
	}

	@Override
	public void debug(final String msg, final Throwable t) {
		log(Level.DEBUG, msg, t);
	}

	@Override
	public void error(final String msg) {
		log(Level.ERROR, msg);
	}

	@Override
	public void error(final String format, final Object arg) {
		log(Level.ERROR, format, arg);
	}

	@Override
	public void error(final String format, final Object... arguments) {
		log(Level.ERROR, format, arguments);
	}

	@Override
	public void error(final String format, final Object arg1, final Object arg2) {
		log(Level.ERROR, format, arg1, arg2);
	}

	@Override
	public void error(final String msg, final Throwable t) {
		log(Level.ERROR, msg, t);
	}

	@Override
	public void info(final String msg) {
		log(Level.INFO, msg);
	}

	@Override
	public void info(final String format, final Object arg) {
		log(Level.INFO, format, arg);
	}

	@Override
	public void info(final String format, final Object... arguments) {
		log(Level.INFO, format, arguments);
	}

	@Override
	public void info(final String format, final Object arg1, final Object arg2) {
		log(Level.INFO, format, arg1, arg2);
	}

	@Override
	public void info(final String msg, final Throwable t) {
		log(Level.INFO, msg, t);
	}

	@Override
	public boolean isDebugEnabled() {
		return debugEnabled;
	}

	@Override
	public boolean isErrorEnabled() {
		return errorEnabled;
	}

	@Override
	public boolean isInfoEnabled() {
		return infoEnabled;
	}

	@Override
	public boolean isTraceEnabled() {
		return traceEnabled;
	}

	@Override
	public boolean isWarnEnabled() {
		return warnEnabled;
	}

	abstract public void log(Level level, FormattingTuple msg);

	public void log(final Level level, final String format, final Object... args) {
		log(level, MessageFormatter.arrayFormat(format, args));
	}

	public void setDebugEnabled(final boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}

	public void setErrorEnabled(final boolean errorEnabled) {
		this.errorEnabled = errorEnabled;
	}

	public void setInfoEnabled(final boolean infoEnabled) {
		this.infoEnabled = infoEnabled;
	}

	public void setTraceEnabled(final boolean traceEnabled) {
		this.traceEnabled = traceEnabled;
	}

	public void setWarnEnabled(final boolean warnEnabled) {
		this.warnEnabled = warnEnabled;
	}

	@Override
	public void trace(final String msg) {
		log(Level.TRACE, msg);
	}

	@Override
	public void trace(final String format, final Object arg) {
		log(Level.TRACE, format, arg);
	}

	@Override
	public void trace(final String format, final Object... arguments) {
		log(Level.TRACE, format, arguments);
	}

	@Override
	public void trace(final String format, final Object arg1, final Object arg2) {
		log(Level.TRACE, format, arg1, arg2);
	}

	@Override
	public void trace(final String msg, final Throwable t) {
		log(Level.TRACE, msg, t);
	}

	@Override
	public void warn(final String msg) {
		log(Level.WARN, msg);
	}

	@Override
	public void warn(final String format, final Object arg) {
		log(Level.WARN, format, arg);
	}

	@Override
	public void warn(final String format, final Object... arguments) {
		log(Level.WARN, format, arguments);
	}

	@Override
	public void warn(final String format, final Object arg1, final Object arg2) {
		log(Level.WARN, format, arg1, arg2);
	}

	@Override
	public void warn(final String msg, final Throwable t) {
		log(Level.WARN, msg, t);
	}
}
