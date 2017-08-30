package com.psg.ihsserver.util;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.nio.file.AtomicMoveNotSupportedException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.internal.inject.ParameterInjectionBinder;

import com.psg.ihsserver.bean.AppointmentBean;
import com.psg.ihsserver.bean.DepartmentBean;
import com.psg.ihsserver.bean.DoctorBean;
import com.psg.ihsserver.bean.PatientBean;
import com.psg.ihsserver.daoimpl.PatientDaoImpl;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.exception.ApplicationException;

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

	public static Date generateSQLDateTime(String dateStr, String format) {
		if (logger.isDebugEnabled())
			logger.debug("generateSQLDate from " + dateStr);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
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
			// ts = new Timestamp(date.getTime());

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

	public static java.util.Date generateUtilDate(java.sql.Timestamp ts) {

		return new java.util.Date(ts.getTime());

	}

	public static java.util.Date generateUtilDate(String str) {

		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date date = null;
		try {
			date = formatter2.parse(str);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return date;
	}

	public static String encrypt(String value) throws ApplicationException{
		String encryptedtext = null;
		try {
			String Input = value;
			SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(Input.getBytes());
			encryptedtext = DatatypeConverter.printBase64Binary(encrypted);
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}

		return encryptedtext;
	}

	public static String decrypt(String value) throws ApplicationException {

		String decrypted = null;
		try {
			String Input = value;
			SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(Input)));
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		return decrypted;
	}

	public static String getAuthToken() {
		return Base64.getEncoder()
				.encodeToString(new String(Strings.CLIENT_ID + ":" + Strings.CLIENT_SECRET).getBytes());
	}

	public static DoctorBean convertToBean(Doctor doctor) {
		DepartmentBean dept = new DepartmentBean(doctor.getDepartment().getDept_no(),
				doctor.getDepartment().getDept_name());

		DoctorBean doc = new DoctorBean(doctor.getDoc_no(), doctor.getDoc_name(), doctor.getDoc_fee(),
				doctor.getDoc_availability(), dept);
		return doc;

	}

	public static DepartmentBean convertToBean(Department department) {
		DepartmentBean dept = new DepartmentBean(department.getDept_no(), department.getDept_name());
		return dept;
	}

	public static PatientBean convertToBean(Patient patient){
		Date sqldate= new Date(patient.getDob().getTime());
		PatientBean patientBean= new PatientBean(patient.getOnline_reg_no(), patient.getOp_code(), patient.getPatient_name(),
				patient.getPatient_pwd(), patient.getSex(), sqldate, patient.getMarital_status(),
				patient.getOccupation(), patient.getAadhaar_no(), patient.getAddress(), patient.getArea_name(),
				patient.getCity_name(), patient.getState(), patient.getPincode(), patient.getPhone(),
				patient.getMobile_no(), patient.getMail_id(), patient.getDependent_type(), patient.getDependent_name(),
				patient.getDependent_occupation(), patient.getDependent_aadhaar_no(), patient.getDiv_code());
		return patientBean;
	}
	public static AppointmentBean convertToBean(Appointment appointment){
		
		java.util.Date app_date =  new Date(appointment.getApp_date().getTime());
		AppointmentBean appointmentBean = new AppointmentBean(appointment.getApp_id(), appointment.getOnline_reg_no(),
				appointment.getOp_code(), app_date, appointment.getCons_dept(), appointment.getCons_dr_no(),
				appointment.getSer_code(), appointment.getReceipt_code(), appointment.getCharge(),
				appointment.getReceipt_status(), appointment.getBill_code(), appointment.getVisit_status(),
				appointment.getApp_status(), appointment.getDiv_code(), appointment.getTx_statusMsg(), appointment.getTx_statusCode(),
				appointment.getTx_amount(), appointment.getTx_dateTime(), appointment.getTx_merchantTxId(), appointment.getTx_merchantCode(),
				appointment.getTx_paymentMethod(), appointment.getTx_pg_id(), appointment.getTx_refundId(), appointment.getTx_checkoutObj());
	
		return appointmentBean;
	
	}
	public static Appointment convertToEntity(AppointmentBean appBean) {
		Appointment app = new Appointment();
		app.setApp_id(appBean.getApp_id());
		app.setApp_date(Utils.generateSQLDate(appBean.getApp_date()));
		app.setApp_status(appBean.getApp_status());
		app.setCons_dept(appBean.getCons_dept());
		app.setCons_dr_no(appBean.getCons_dr_no());
		app.setBill_code(appBean.getBill_code());
		app.setCharge(appBean.getCharge());
		app.setOnline_reg_no(appBean.getOnline_reg_no());
		app.setOp_code(appBean.getOp_code());
		app.setDiv_code(appBean.getDiv_code());
		app.setReceipt_code(appBean.getReceipt_code());
		app.setReceipt_status(appBean.getReceipt_status());
		app.setSer_code(appBean.getSer_code());
		app.setVisit_status(appBean.getVisit_status());
		app.setTx_statusCode(appBean.getTx_statusCode());
		app.setTx_statusMsg(appBean.getTx_statusMsg());
		app.setTx_amount(appBean.getTx_amount());
		app.setTx_dateTime(appBean.getTx_dateTime());
		app.setTx_merchantTxId(appBean.getTx_merchantTxId());
		app.setTx_merchantCode(appBean.getTx_merchantCode());
		app.setTx_paymentMethod(appBean.getTx_paymentMethod());
		app.setTx_pg_id(appBean.getTx_pg_id());
		app.setTx_refundId(appBean.getTx_refundId());
		app.setTx_checkoutObj(appBean.getTx_checkoutObj());

		return app;
	}

	public static boolean mobileNoValidator(String mobileNo) {

		boolean check = false;
		// String mobileNo = editText.getText().toString();
		if (Pattern.matches("[2-9]{2}\\d{8}", mobileNo)) {
			// if (mobileNo.length() != 10) {
			// check = false;
			// //
			// editText.setError(BaseApplication.getContext().getResources().getSystem().getString(R.string.valErr_invalid_mobile_no));
			// } else {
			// check = true;
			// }

			check = true;
		} else {
			check = false;
		}
		return check;
	}

	public static String generateTxId(String opCode, String pdate) {
		String transactionId = null;
		long currentTime=System.currentTimeMillis()/1000;
		String 	s=Long.toString(currentTime);
		transactionId=opCode.substring(3)+s;
		return transactionId;
	}

	public static String generateOTP(String opCode) throws ApplicationException {

		String seed = opCode.substring(1);
		String otp = null;
		long T0 = 0;
		long X = 300;
		long currentTime = System.currentTimeMillis();
		String steps = "0";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("IST"));

		try {
			long T = (currentTime - T0) / X;
			steps = Long.toHexString(T).toUpperCase();
			while (steps.length() < 16)
				steps = "0" + steps;
			String otpDigits = "4";
			int codeDigits = Integer.decode(otpDigits).intValue();
			String result = null;

			// Using the counter
			// First 8 bytes are for the movingFactor
			// Compliant with base RFC 4226 (HOTP)
			while (steps.length() < 16)
				steps = "0" + steps;

			byte[] bArray = new BigInteger("10" + steps, 16).toByteArray();
			byte[] ret = new byte[bArray.length - 1];
			for (int i = 0; i < ret.length; i++)
				ret[i] = bArray[i + 1];
			byte[] msg = ret;

			bArray = new BigInteger("10" + seed, 16).toByteArray();
			ret = new byte[bArray.length - 1];
			for (int i = 0; i < ret.length; i++)
				ret[i] = bArray[i + 1];
			byte[] k = ret;
			
			// generating time based OTP
			Mac hmac;
			hmac = Mac.getInstance("HmacSHA512");
			SecretKeySpec macKey = new SecretKeySpec(k, "RAW");
			hmac.init(macKey);
			byte[] hash = hmac.doFinal(msg);
			// put selected bytes into result int
			int offset = hash[hash.length - 1] & 0xf;
			int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16)
					| ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);
			int otp1 = binary % DIGITS_POWER[codeDigits];
			result = Integer.toString(otp1);
			while (result.length() < codeDigits) {
				result = "0" + result;
			}
			otp = result;

		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		return otp;
	}

	private static final int[] DIGITS_POWER
	// 0 1 2 3 4 5 6 7 8
			= { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };
}
