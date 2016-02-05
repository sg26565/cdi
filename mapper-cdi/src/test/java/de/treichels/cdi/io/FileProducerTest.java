package com.bayerbbs.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.bayerbbs.testing.CdiTestRunner;

@RunWith(CdiTestRunner.class)
public class FileProducerTest {
	@Inject
	@Temporary
	private File f1;

	@Inject
	@Temporary(name = "foo")
	private File f2;

	@Test
	public void test() {
		assertNotNull(f1);
		assertTrue(f1.exists());
		assertFalse(f1.isDirectory());
		assertEquals(0, f1.length());
		assertTrue(f1.getName().startsWith(FileProducerTest.class.getSimpleName()));
		assertTrue(f1.getName().endsWith(".tmp"));

		assertNotNull(f2);
		assertTrue(f2.exists());
		assertFalse(f2.isDirectory());
		assertEquals(0, f2.length());
		assertTrue(f2.getName().startsWith("foo"));
		assertTrue(f2.getName().endsWith(".tmp"));
	}
}
