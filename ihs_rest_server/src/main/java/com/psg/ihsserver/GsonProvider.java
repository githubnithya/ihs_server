package com.psg.ihsserver;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object>{

	private final Gson gson;

	  public GsonProvider() {
	    gson = new Gson();
	    System.out.println("Created GsonProvider");
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
