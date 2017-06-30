package com.psg.ihsserver.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.psg.ihsserver.daoimpl.PatientDaoImpl;

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
}
