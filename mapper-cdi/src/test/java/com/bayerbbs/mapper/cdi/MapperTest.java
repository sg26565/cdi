package com.bayerbbs.mapper.cdi;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bayerbbs.mapper.Mapper;

@RunWith(CdiRunner.class)
@AdditionalClasses({ StringToBooleanMapper.class, StringToDoubleMapper.class, StringToFloatMapper.class,
		StringToIntMapper.class })
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
