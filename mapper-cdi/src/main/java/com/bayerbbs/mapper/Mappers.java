package com.bayerbbs.mapper;

import java.util.function.Function;

import javax.enterprise.inject.Produces;

public class Mappers {
	@Produces
	public static final Function<String, Boolean> STRING_TO_BOOLEAN = s -> Boolean.valueOf(s.trim());

	@Produces
	public static final Function<String, Byte> STRING_TO_BYTE = s -> Byte.valueOf(s.trim());

	@Produces
	public static final Function<String, Double> STRING_TO_DOUBLE = s -> Double.valueOf(s.trim());

	@Produces
	public static final Function<String, Short> STRING_TO_SHORT = s -> Short.valueOf(s.trim());

	@Produces
	public static final Function<String, Float> STRING_TO_FLOAT = s -> Float.valueOf(s.trim());

	@Produces
	public static final Function<String, Integer> STRING_TO_INTEGER = s -> Float.valueOf(s.trim()).intValue();
}
