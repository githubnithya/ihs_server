package com.psg.ihsserver.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.psg.ihsserver.daoimpl.PatientDaoImpl;

public class Utils {
	
	
	private static final Logger logger = Logger.getLogger(Utils.class);

	public static Date generateSQLDate(String dateStr)
	{
		if(logger.isDebugEnabled())
			logger.debug("generateSQLDate from " + dateStr);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		java.util.Date date;
		java.sql.Date sqlDOB = null;
		try {
			date = formatter.parse(dateStr);
			 sqlDOB = new java.sql.Date(date.getTime());  
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlDOB;
	}
	
	public static Date generateSQLDate(java.util.Date utilDate)
	{
		if(logger.isDebugEnabled())
			logger.debug("generateSQLDate from " + utilDate);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = formatter.format(utilDate);
		formatter.setTimeZone(TimeZone.getTimeZone("IST"));
		java.util.Date date;
		java.sql.Date sqlDOB = null;
		try {
			date = formatter.parse(strDate);
			 sqlDOB = new java.sql.Date(date.getTime());  
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlDOB;
	}
}
