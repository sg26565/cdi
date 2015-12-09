package com.bayerbbs.mapper;

public abstract class Mapper<FROM, TO> {
	public abstract TO map(FROM from);
}
