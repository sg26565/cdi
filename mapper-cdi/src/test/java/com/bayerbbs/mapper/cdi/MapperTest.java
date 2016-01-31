package com.bayerbbs.mapper.cdi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Function;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.bayerbbs.testing.CdiTestRunner;

@RunWith(CdiTestRunner.class)
public class MapperTest {
	@Inject
	private Function<String, Integer> intMapper;

	@Inject
	private Function<String, Float> floatMapper;

	@Inject
	private Function<String, Double> doubleMapper;

	@Inject
	private Function<String, Boolean> booleanMapper;

	@Test
	public void test() {
		final String s = "3.14";

		assertNotNull(intMapper);
		assertEquals(3, (int) intMapper.apply(s));

		assertNotNull(floatMapper);
		assertEquals(3.14f, floatMapper.apply(s), 0f);

		assertNotNull(doubleMapper);
		assertEquals(3.14d, doubleMapper.apply(s), 0d);

		assertNotNull(booleanMapper);
		assertFalse(booleanMapper.apply("False"));
		assertFalse(booleanMapper.apply("false"));
		assertFalse(booleanMapper.apply("herbert"));
		assertTrue(booleanMapper.apply("True"));
		assertTrue(booleanMapper.apply("true"));
	}
}
