package com.bayerbbs.env;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.NoSuchElementException;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bayerbbs.testing.CdiTestRunner;

@RunWith(CdiTestRunner.class)
public class EnvProducerTest {
	private static class EnvironmentLiteral extends AnnotationLiteral<Environment> implements Environment {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean strict() {
			return true;
		}

		@Override
		public String value() {
			return "doesnotexist";
		}
	}

	private static class SystemPropertyLiteral extends AnnotationLiteral<SystemProperty> implements SystemProperty {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean strict() {
			return true;
		}

		@Override
		public String value() {
			return "doesnotexist";
		}
	}

	@Inject
	@Environment(value = "USERPROFILE", strict = true)
	private String userProfile;

	@Inject
	@SystemProperty(value = "java.io.tmpdir", strict = true)
	private String tmpDir;

	@Inject
	@Environment("doesnotexist")
	private String doesNotExist1;

	@Inject
	@SystemProperty("doesnotexist")
	private String doesNotExist2;

	@Test
	public void testEvnorinment() {
		assertNotNull(userProfile);
		final File userProfileFile = new File(userProfile);
		assertTrue(userProfileFile.exists());
		assertTrue(userProfileFile.isDirectory());
	}

	@Test(expected = NoSuchElementException.class)
	public void testStrictEnvironment() {
		assertNull(doesNotExist1);

		final WeldContainer container = CdiTestRunner.getContainer();

		final Instance<String> select = container.select(String.class, new EnvironmentLiteral());

		assertNotNull(select);

		final String string = select.get();
		fail(string);
	}

	@Test(expected = NoSuchElementException.class)
	public void testStrictSystemProperty() {
		assertNull(doesNotExist2);

		final WeldContainer container = CdiTestRunner.getContainer();

		final Instance<String> select = container.select(String.class, new SystemPropertyLiteral());

		assertNotNull(select);

		final String string = select.get();
		fail(string);
	}

	@Test
	public void testSystemProperty() {
		assertNotNull(tmpDir);
		final File tmpDirFile = new File(tmpDir);
		assertTrue(tmpDirFile.exists());
		assertTrue(tmpDirFile.isDirectory());
	}
}
