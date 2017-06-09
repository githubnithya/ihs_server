package com.psg.ihsserver.test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Utils {

	public Date generateSQLDate(String dateStr)
	{
		//String startDate="24-06-1987";
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
}
