package de.treichels.cdi.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Function;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.treichels.cdi.testing.CdiTestRunner;
import de.treichels.cdi.tracing.Tracing;

@RunWith(CdiTestRunner.class)
@Tracing
public class MapperTest {
	@Inject
	private Function<String, Integer> intMapper;

	@Inject
	private Function<String, Float> floatMapper;

	@Inject
	private Function<String, Double> doubleMapper;

	@Inject
	private Function<String, Boolean> booleanMapper;

	private final String string = "3.14";

	@Test
	public void testBooleanMapper() {
		assertNotNull(booleanMapper);
		assertFalse(booleanMapper.apply("False"));
		assertFalse(booleanMapper.apply("false"));
		assertFalse(booleanMapper.apply("herbert"));
		assertTrue(booleanMapper.apply("True"));
		assertTrue(booleanMapper.apply("true"));
	}

	@Test
	public void testDoupleMapper() {
		assertNotNull(doubleMapper);
		assertEquals(3.14d, doubleMapper.apply(string), 0d);
	}

	@Test
	public void testFloatMapper() {
		assertNotNull(floatMapper);
		assertEquals(3.14f, floatMapper.apply(string), 0f);
	}

	@Test
	public void testIntMapper() {
		assertNotNull(intMapper);
		assertEquals(3, (int) intMapper.apply(string));
	}
}
