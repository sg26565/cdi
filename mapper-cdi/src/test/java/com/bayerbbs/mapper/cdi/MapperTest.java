package com.bayerbbs.mapper.cdi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.bayerbbs.mapper.Mapper;
import com.bayerbbs.testing.CdiTestRunner;

@RunWith(CdiTestRunner.class)
public class MapperTest {
	@Inject
	private Mapper<String, Integer> intMapper;

	@Inject
	private Mapper<String, Float> floatMapper;

	@Inject
	private Mapper<String, Double> doubleMapper;

	@Inject
	private Mapper<String, Boolean> booleanMapper;

	@Test
	public void test() {
		String s = "3.14";

		assertNotNull(intMapper);
		assertEquals(3, (int) intMapper.map(s));

		assertNotNull(floatMapper);
		assertEquals(3.14f, floatMapper.map(s), 0f);

		assertNotNull(doubleMapper);
		assertEquals(3.14d, doubleMapper.map(s), 0d);

		assertNotNull(booleanMapper);
		assertFalse(booleanMapper.map("False"));
		assertFalse(booleanMapper.map("false"));
		assertFalse(booleanMapper.map("herbert"));
		assertTrue(booleanMapper.map("True"));
		assertTrue(booleanMapper.map("true"));
	}
}
