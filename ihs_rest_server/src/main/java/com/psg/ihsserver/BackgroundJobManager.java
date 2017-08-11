package com.psg.ihsserver;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.psg.ihsserver.util.HibernateUtil;

/**
 * Created by Dhiviya Devarajan on 8/4/2017.
 */
@WebListener
public class BackgroundJobManager implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		scheduler.shutdownNow();
		HibernateUtil.closeFactory();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		 scheduler = Executors.newSingleThreadScheduledExecutor();
	}

}
