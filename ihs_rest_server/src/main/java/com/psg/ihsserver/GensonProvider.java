package com.psg.ihsserver;

import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;


@Provider
public class GensonProvider implements ContextResolver<Genson>{
	 private final Genson genson = new GensonBuilder()
	 		//.exclude("dob")
	 		//.registerDeserializer((Deserializer<T>) new DateDeserializer(), Date.class)
	 		.useDateAsTimestamp(false)
			 .useDateFormat(new SimpleDateFormat("dd-MM-yyyy"))
			 .create();

	 @Override
	public Genson getContext(Class<?> type) {
		 System.out.println("Called GetContext");
	 return genson;
	}
}
