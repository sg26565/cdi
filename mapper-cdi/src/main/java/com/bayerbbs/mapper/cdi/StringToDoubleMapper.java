package com.bayerbbs.mapper.cdi;

import javax.enterprise.context.ApplicationScoped;

import com.bayerbbs.mapper.Mapper;
import com.bayerbbs.tracing.Tracing;

@ApplicationScoped
public class StringToDoubleMapper extends Mapper<String, Double> {
	@Override
	@Tracing
	public Double map(final String from) {
		return Double.valueOf(from);
	}
}
