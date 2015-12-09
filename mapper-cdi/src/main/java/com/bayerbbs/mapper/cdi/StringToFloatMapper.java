package com.bayerbbs.mapper.cdi;

import javax.enterprise.context.ApplicationScoped;

import com.bayerbbs.mapper.Mapper;
import com.bayerbbs.tracing.Tracing;

@ApplicationScoped
public class StringToFloatMapper extends Mapper<String, Float> {
	@Override
	@Tracing
	public Float map(final String from) {
		return Float.valueOf(from);
	}
}
