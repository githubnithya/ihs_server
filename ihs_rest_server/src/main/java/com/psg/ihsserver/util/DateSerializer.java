package com.psg.ihsserver.util;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializer implements JsonSerializer {

	@Override
	public JsonElement serialize(Object date, Type arg1, JsonSerializationContext arg2) {
		//TODO 
		return date == null ? null : new JsonPrimitive("");
	}
	

}
