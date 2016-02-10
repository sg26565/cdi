package de.treichels.cdi.mapper;

import java.util.function.Function;

import javax.enterprise.inject.Produces;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.spi.StandardLevel;

public class Mappers {
	@Produces
	public static final Function<String, Boolean> STRING_TO_BOOLEAN = s -> Boolean.valueOf(s.trim());

	@Produces
	public static final Function<String, Byte> STRING_TO_BYTE = s -> Byte.valueOf(s.trim());

	@Produces
	public static final Function<String, Double> STRING_TO_DOUBLE = s -> Double.valueOf(s.trim());

	@Produces
	public static final Function<String, Float> STRING_TO_FLOAT = s -> Float.valueOf(s.trim());

	@Produces
	public static final Function<String, Integer> STRING_TO_INTEGER = s -> Float.valueOf(s.trim()).intValue();

	@Produces
	public static final Function<String, Long> STRING_TO_LONG = s -> Double.valueOf(s.trim()).longValue();

	@Produces
	public static final Function<String, Short> STRING_TO_SHORT = s -> Short.valueOf(s.trim());

	@Produces
	public static final Function<Level, StandardLevel> LEVEL_TO_STANDARD_LEVEL = l -> StandardLevel.getStandardLevel(l.intLevel());

	@Produces
	public static final Function<StandardLevel, Level> STANDARD_LEVEL_TO_LEVEL = s -> Level.getLevel(s.name());
}
