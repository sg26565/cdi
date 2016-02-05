package de.treichels.cdi.tracing;

@Tracing
public class Bar {
	public int bar(final int b) {
		return 5 * b;
	}
}
