package com.bayerbbs.mapper.cdi;

import javax.enterprise.context.ApplicationScoped;

import com.bayerbbs.mapper.Mapper;
import com.bayerbbs.tracing.Tracing;

@ApplicationScoped
class StringToBooleanMapper extends Mapper<String, Boolean> {
	@Override
	@Tracing
	public Boolean map(final String from) {
		return Boolean.valueOf(from);
	}
}