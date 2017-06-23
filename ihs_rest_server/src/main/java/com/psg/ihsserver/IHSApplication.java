package com.psg.ihsserver;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/ihsserver")
public class IHSApplication extends ResourceConfig {
	
	public IHSApplication()
	{
		this.packages("com.pgs.ihsserver");
		this.register(LoggingFilter.class);
		this.register("GsonProvider.class");
		this.register(new IHSApplicationBinder());
	}

}
