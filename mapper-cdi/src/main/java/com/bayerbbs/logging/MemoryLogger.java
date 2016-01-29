package com.bayerbbs.logging;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.helpers.FormattingTuple;

public class MemoryLogger extends AbstractLoggerBase implements Queue<LogEntry> {
	private static final long serialVersionUID = 1L;

	private final Queue<LogEntry> messages = new LinkedList<LogEntry>();

	public MemoryLogger(final Class<?> clazz) {
		this(clazz.getName());
	}

	public MemoryLogger(final String name) {
		this.name = name;
	}

	@Override
	public boolean add(final LogEntry e) {
		return messages.add(e);
	}

	@Override
	public boolean addAll(final Collection<? extends LogEntry> c) {
		return messages.addAll(c);
	}

	@Override
	public void clear() {
		messages.clear();
	}

	@Override
	public boolean contains(final Object o) {
		return messages.contains(o);
	}

	@Override
	public boolean containsAll(final Collection<?> c) {
		return messages.containsAll(c);
	}

	@Override
	public LogEntry element() {
		return messages.element();
	}

	@Override
	public boolean isEmpty() {
		return messages.isEmpty();
	}

	@Override
	public Iterator<LogEntry> iterator() {
		return messages.iterator();
	}

	@Override
	public void log(final Level level, final FormattingTuple msg) {
		messages.add(new LogEntry(level, msg));
	}

	@Override
	public boolean offer(final LogEntry e) {
		return messages.offer(e);
	}

	@Override
	public LogEntry peek() {
		return messages.peek();
	}

	@Override
	public LogEntry poll() {
		return messages.poll();
	}

	@Override
	public LogEntry remove() {
		return messages.remove();
	}

	@Override
	public boolean remove(final Object o) {
		return messages.remove(o);
	}

	@Override
	public boolean removeAll(final Collection<?> c) {
		return messages.removeAll(c);
	}

	@Override
	public boolean retainAll(final Collection<?> c) {
		return messages.retainAll(c);
	}

	@Override
	public int size() {
		return messages.size();
	}

	@Override
	public Object[] toArray() {
		return messages.toArray();
	}

	@Override
	public <T> T[] toArray(final T[] a) {
		return messages.toArray(a);
	}
}
