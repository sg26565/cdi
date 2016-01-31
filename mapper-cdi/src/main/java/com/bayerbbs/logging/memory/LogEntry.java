package com.bayerbbs.logging.memory;

import org.slf4j.helpers.FormattingTuple;

import com.bayerbbs.logging.memory.AbstractLoggerBase.Level;

public class LogEntry {
	private final Level level;
	private final FormattingTuple message;
	private final long timestamp;
	
	public LogEntry(Level level, FormattingTuple message) {
		this.timestamp = System.currentTimeMillis();
		this.level = level;
		this.message = message;
	}

	public Level getLevel() {
		return level;
	}

	public FormattingTuple getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}
}