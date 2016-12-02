package de.treichels.cdi.env;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.NoSuchElementException;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.apache.commons.lang3.SystemUtils;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.treichels.cdi.testing.CdiTestRunner;
import de.treichels.cdi.tracing.Tracing;

@RunWith(CdiTestRunner.class)
@Tracing
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
	@Environment(value = "USERPROFILE", strict = false)
	private String userProfile;

	@Inject
	@Environment(value = "SHELL", strict = false)
	private String shell;

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
		File file;

		if (SystemUtils.IS_OS_WINDOWS) {
			assertNotNull(userProfile);
			file = new File(userProfile);
			assertTrue(file.exists());
			assertTrue(file.isDirectory());
		} else if (SystemUtils.IS_OS_LINUX) {
			assertNotNull(shell);
			file = new File(shell);
			assertTrue(file.exists());
			assertFalse(file.isDirectory());
			assertTrue(file.canExecute());
		}
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
