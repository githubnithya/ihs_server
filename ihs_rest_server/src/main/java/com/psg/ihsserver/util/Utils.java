package com.psg.ihsserver.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.psg.ihsserver.bean.DepartmentBean;
import com.psg.ihsserver.bean.DoctorBean;
import com.psg.ihsserver.daoimpl.PatientDaoImpl;
import com.psg.ihsserver.entity.Doctor;

public class Utils {

	private static final Logger logger = Logger.getLogger(Utils.class);
	protected static final String UTF8 = "utf-8";
	protected static final String key = "SSgpWdw5SSgpWdw6";

	public static Date generateSQLDate(String dateStr) {
		if (logger.isDebugEnabled())
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

	public static Date generateSQLDateTime(String dateStr) {
		if (logger.isDebugEnabled())
			logger.debug("generateSQLDate from " + dateStr);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
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
	
	public static String changeTSFormat(Timestamp timeSt) {
	       
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        formatter2.setTimeZone(TimeZone.getTimeZone("IST"));
       
        String strDate = formatter2.format(timeSt);

        return strDate;
    }
    
    public static Long generateSQLTimeStamp(String dateStr) {
		
		 SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		formatter2.setTimeZone(TimeZone.getTimeZone("IST"));
		java.util.Date date = null;
		Timestamp ts = null;
		try {
			date = formatter2.parse(dateStr);
			System.out.println("date.getTime() " + date.getTime());
//			ts = new Timestamp(date.getTime());
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime();
	}
	public static Date generateSQLDate(java.util.Date utilDate) {
		if (logger.isDebugEnabled())
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

	public static java.util.Date generateUtilDate(java.sql.Timestamp ts)
	{
		
		return new java.util.Date(ts.getTime());
		
	}
	
	public static java.util.Date generateUtilDate(String str)
	{
		
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date date = null;
		try {
			date = formatter2.parse(str);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return date;
	}
	static String encrypt(String value) {
		String encryptedtext = null;
		try {

			String Input = value;

			SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(Input.getBytes());
			encryptedtext = DatatypeConverter.printBase64Binary(encrypted);
			System.err.println("encrypted:" + encryptedtext);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptedtext;
	}

	static String decrypt(String value) {

		String decrypted = null;
		try {

			String Input = value;

			SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(Input)));
			System.err.println("decrypted: " + decrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return decrypted;
	}

	public static String getAuthToken()
	{
		return Base64.getEncoder().encodeToString(new String(Strings.CLIENT_ID + ":" +Strings.CLIENT_SECRET).getBytes());
	}
	
	public static DoctorBean convertToBean(Doctor doctor)
	{
		DepartmentBean dept = new DepartmentBean(doctor.getDepartment().getDept_no(), doctor.getDepartment().getDept_name());
		
		DoctorBean doc = new DoctorBean(doctor.getDoc_no(), doctor.getDoc_name(), dept);
		return doc;
		
	}
	
	public static boolean mobileNoValidator(String mobileNo) {
       
        boolean check = false;
        //String mobileNo = editText.getText().toString();
        if (Pattern.matches("[2-9]{2}\\d{8}", mobileNo)) {
//            if (mobileNo.length() != 10) {
//                check = false;
//                // editText.setError(BaseApplication.getContext().getResources().getSystem().getString(R.string.valErr_invalid_mobile_no));
//            } else {
//                check = true;
//            }
            
            check = true;
        } else {
            check = false;
        }
        return check;
    }
}
