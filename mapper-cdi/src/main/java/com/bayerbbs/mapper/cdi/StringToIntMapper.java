package com.bayerbbs.mapper.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.bayerbbs.mapper.Mapper;
import com.bayerbbs.tracing.Tracing;

@ApplicationScoped
public class StringToIntMapper extends Mapper<String, Integer> {
	@Inject
	private Mapper<String, Float> floatMapper;

	@Override
	@Tracing
	public Integer map(final String from) {
		return floatMapper.map(from).intValue();
	}
}
