package com.bayerbbs.tracing;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.bayerbbs.testing.CdiTestRunner;
import com.bayerbbs.tracing.Tracing.Level;

@RunWith(CdiTestRunner.class)
public class TracingTest {
	@Test
	@Tracing(level=Level.INFO)
	public void test() {
	}
}
