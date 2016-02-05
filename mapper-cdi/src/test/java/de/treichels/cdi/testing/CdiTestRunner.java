package com.bayerbbs.testing;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class CdiTestRunner extends BlockJUnit4ClassRunner {
	private static final Weld WELD = new Weld(CdiTestRunner.class.getName());
	private static final WeldContainer container = WELD.initialize();

	public static WeldContainer getContainer() {
		return container;
	}

	public static Weld getWeld() {
		return WELD;
	}

	public CdiTestRunner(final Class<?> klass) throws InitializationError {
		super(klass);
		classRules().add(new CdiTestRule());
	}

	@Override
	protected Object createTest() throws Exception {
		return container.select(getTestClass().getJavaClass()).get();
	}
}
