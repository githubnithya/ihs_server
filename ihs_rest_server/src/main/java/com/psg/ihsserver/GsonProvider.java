package com.psg.ihsserver;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object>{

	private static final Logger logger = Logger.getLogger(GsonProvider.class);
	private final Gson gson;
	private SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");

	  public GsonProvider() {
		  GsonBuilder gsonBuilder = new GsonBuilder();
		  gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { 
			   public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				      try {
				    	  logger.info(dtf.parse(json.getAsString()));
						return dtf.parse(json.getAsString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block - replace loggin
						//e.printStackTrace();
						logger.error(e.getMessage());
					}
					return null;
				   } 
				});
		  gsonBuilder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {

			@Override
			public JsonElement serialize(Date date, Type arg1, JsonSerializationContext arg2) {
				return date == null ? null : new JsonPrimitive(date.getTime());
			} 
			   
				});
	    gson = gsonBuilder
	    		//.setDateFormat("dd-MM-yyyy")
	    		.create();
	    
	    logger.info("Created GsonProvider");
	  }
	  
	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type arg1, Annotation[] arg2, MediaType arg3, MultivaluedMap<String, String> arg4,
			InputStream entityStream) throws IOException, WebApplicationException {
		
		InputStreamReader reader = new InputStreamReader( entityStream, "UTF-8" );
	    try {
	      return gson.fromJson( reader, type );
	    } finally {
	      reader.close();
	    }
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void writeTo(Object object, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4,
			MultivaluedMap<String, Object> arg5, OutputStream entityStream) throws IOException, WebApplicationException {
		 PrintWriter printWriter = new PrintWriter( entityStream );
		    try {
		      String json = gson.toJson( object );
		      printWriter.write( json );
		      printWriter.flush();
		    } finally {
		      printWriter.close();
		    }
		
	}

	@Override
	public long getSize(Object arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// TODO Auto-generated method stub
		return -1;
	}

}
