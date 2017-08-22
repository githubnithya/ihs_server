package com.psg.ihsserver;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;
import com.psg.ihsserver.util.HibernateUtil;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;

/**
 * Created by Dhiviya Devarajan on 8/4/2017.
 */
@WebListener
public class BackgroundJobManager implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(BackgroundJobManager.class);
	private ScheduledExecutorService scheduler;
	public static CacheManager cacheMgr = null;
	public static final String cacheName = "OtpCache";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		scheduler.shutdownNow();
		HibernateUtil.closeFactory();
		if (cacheMgr != null)
			cacheMgr.shutdown();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		scheduler = Executors.newSingleThreadScheduledExecutor();
		try {
			cacheMgr = CacheManager.create();
			CacheConfiguration cacheConfiguration = new CacheConfiguration();
			cacheConfiguration.setName(cacheName);
			cacheConfiguration.maxEntriesLocalHeap(500000);
			cacheConfiguration.timeToIdleSeconds(300); // 5 mins
			cacheConfiguration.timeToLiveSeconds(600); // 10 mins
			cacheConfiguration.diskSpoolBufferSizeMB(20);
			cacheConfiguration.memoryStoreEvictionPolicy("LFU");
			cacheConfiguration.diskExpiryThreadIntervalSeconds(8640000);
			Cache cache = new Cache(cacheConfiguration);
			cacheMgr.addCache(cache);
		} catch (CacheException e) {
			logger.error(e.getLocalizedMessage());
		}
		logger.info("Cache Exists " + cacheName + " :" + cacheMgr.cacheExists(cacheName));
	}

}
