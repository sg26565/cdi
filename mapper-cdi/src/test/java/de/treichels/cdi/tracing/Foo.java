package de.treichels.cdi.tracing;

import javax.inject.Inject;

@Tracing
public class Foo {
	@Inject
	private Bar bar;

	public int foo(final int a) {
		return bar.bar(3 * a);
	}
}
