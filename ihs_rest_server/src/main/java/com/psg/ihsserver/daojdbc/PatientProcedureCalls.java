package com.psg.ihsserver.daojdbc;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.psg.ihsserver.BackgroundJobManager;

import org.apache.log4j.Logger;

import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.exception.ApplicationException;

import oracle.jdbc.OracleTypes;

public class PatientProcedureCalls {
	public static String DB_DRIVER;
	public static String DB_CONNECTION;
	public static String DB_USER;
	public static String DB_PASSWORD;
	
	
	Connection dbConnection = null;
	CallableStatement callableStatement = null;
	
	private static final Logger logger = Logger.getLogger(PatientProcedureCalls.class);

		private static Connection getDBConnection() {
		Connection connection = null;
		try {

			Class.forName(BackgroundJobManager.DB_DRIVER);
			connection = DriverManager.getConnection(BackgroundJobManager.DB_CONNECTION, BackgroundJobManager.DB_USER, BackgroundJobManager.DB_PASSWORD);
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;

	}

	public String forgotOpCode(String patient_name, String dob, String mobile_no) throws ApplicationException {

		int resultOnProcedureCall=0;
		String opCode=null;
		String procedureCall = "{call FORGOTOPCODE(?,?,?,?)}";
		try{
			System.out.println("data: "+patient_name+ " "+dob+" "+mobile_no);
		dbConnection = getDBConnection();
		System.out.println("got connection");
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date sqldate = new Date(df.parse(dob).getTime());
		
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setString(1, patient_name);
		callableStatement.setDate(2, sqldate);
		callableStatement.setString(3, mobile_no);
		callableStatement.registerOutParameter(4, Types.VARCHAR);
		
		resultOnProcedureCall=callableStatement.executeUpdate();
		logger.info("Result on procedure call: "+resultOnProcedureCall);
		opCode=callableStatement.getString(4);
		logger.info("OPCODE: "+opCode);
		return opCode;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	public String isPatient(String opCode) throws ApplicationException {
		int resultOnProcedureCall=0;
		String mobile=null;
		String procedureCall = "{call ISPATIENT(?,?)}";
		try{
		dbConnection = getDBConnection();
		System.out.println("got connection");
		
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setString(1, opCode);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		
		resultOnProcedureCall=callableStatement.executeUpdate();
		logger.info("Result on procedure call: "+resultOnProcedureCall);
		mobile=callableStatement.getString(2);
		logger.info("mobile number : "+mobile);
		return mobile;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	
	public boolean changePassword(String opCode, String password) throws ApplicationException {
	int resultOnProcedureCall=0;
	String procedureCall = "{call UPDATEPATIENTPASSWORD(?,?)}";
	try{
	dbConnection = getDBConnection();
	System.out.println("got connection");
	callableStatement = dbConnection.prepareCall(procedureCall);
	callableStatement.setString(1, opCode);
	callableStatement.setString(2, password);
	resultOnProcedureCall=callableStatement.executeUpdate();
	logger.info("Result on procedure call: "+resultOnProcedureCall);
	return true;
	}catch (Exception e) {
		logger.error(e.getMessage());
		e.printStackTrace();
		throw new ApplicationException(e.getMessage());
	}
	
}
	
	public Patient login(String opCode, String password) throws ApplicationException {
		String procedureCall = "{call LOGIN(?,?,?)}";
		try{
		dbConnection = getDBConnection();
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setString(1, opCode);
		callableStatement.setString(2, password);
		callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		callableStatement.executeQuery();
		ResultSet rs = (ResultSet) callableStatement.getObject(3);
		Patient p=null;
		while(rs.next()){
			p=new Patient();
			p.setOnline_reg_no(rs.getString("MRDMCG_ONLINE_REG_NO"));
			p.setOp_code(rs.getString("MRDMCG_OP_CODE"));
			p.setPatient_name(rs.getString("MRDMCG_PATIENT_NAME"));
			p.setPatient_pwd(rs.getString("MRDMCG_PWD"));
			p.setSex(rs.getString("MRDMCG_SEX"));
			p.setDob(rs.getDate("MRDMCG_DOB"));
			p.setMarital_status(rs.getString("MRDMCG_MARITAL_STATUS"));
			p.setOccupation(rs.getString("MRDMCG_OCCUPATION"));
			p.setAadhaar_no(java.math.BigInteger.valueOf(rs.getLong("MRDMCG_AADHAAR_NO")));
			p.setAddress(rs.getString("MRDMCG_ADDRESS"));
			p.setArea_name(rs.getString("MRDMCG_AREA_NAME"));
			p.setCity_name(rs.getString("MRDMCG_CITY_NAME"));
			p.setState(rs.getString("MRDMCG_STATE"));
			p.setPincode(rs.getString("MRDMCG_PINCODE"));
			p.setPhone(java.math.BigInteger.valueOf(rs.getLong("MRDMCG_PHONE")));
			p.setMobile_no(java.math.BigInteger.valueOf(rs.getLong("MRDMCG_MOBILE_NO")));
			p.setMail_id(rs.getString("MRDMCG_MAIL_ID"));
			p.setDependent_type(rs.getString("MRDMCG_DEPENDENT_TYPE"));
			p.setDependent_name(rs.getString("MRDMCG_DEPENDENT_NAME"));
			p.setDependent_occupation(rs.getString("MRDMCG_DEPENDENT_OCCUPATION"));
			p.setDependent_aadhaar_no(java.math.BigInteger.valueOf(rs.getLong("MRDMCG_DEPENDENT_AADHAAR_NO")));
			p.setDiv_code(rs.getString("MRDMCG_DIV_CODE"));
}
		return p;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	public int callProcedure1() {
		int res=0;
		try {
			String procedureCall = "{call UPDATEPATIENTPASSWORD(?,?,?)}";
			dbConnection = getDBConnection();
			System.out.println("got connection");
			callableStatement = dbConnection.prepareCall(procedureCall);
			callableStatement.setString(1, "O17123456");
			callableStatement.setString(2, "faruq");
			//callableStatement.setString(3, "392932782991");
			//callableStatement.setString(4, "666666666");
			callableStatement.registerOutParameter(3, Types.INTEGER);

			System.out.println("executing..");
			 res=callableStatement.executeUpdate();
			 System.out.println("res: "+res);
			 int result=callableStatement.getInt(3);
			System.out.println(result);

			/*
			 * String userName = callableStatement.getString(2); String
			 * createdBy = callableStatement.getString(3); Date createdDate =
			 * callableStatement.getDate(4);
			 * 
			 * System.out.println("UserName : " + userName);
			 * System.out.println("CreatedBy : " + createdBy);
			 * System.out.println("CreatedDate : " + createdDate);
			 */
			return res;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			
			return res;


		} finally {

			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		

	}
	public Patient getPatientByOpCode(String opCode) throws ApplicationException {
		// TODO Auto-generated method stub
		String procedureCall = "{call GETPATIENT(?,?)}";
		try{
		dbConnection = getDBConnection();
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setString(1, opCode);
		callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		callableStatement.executeQuery();
		ResultSet rs = (ResultSet) callableStatement.getObject(3);
		Patient p=null;
		while(rs.next()){
			p=new Patient();
			p.setOnline_reg_no(rs.getString("MRDMCG_ONLINE_REG_NO"));
			p.setOp_code(rs.getString("MRDMCG_OP_CODE"));
			p.setPatient_name(rs.getString("MRDMCG_PATIENT_NAME"));
			p.setPatient_pwd(rs.getString("MRDMCG_PWD"));
			p.setSex(rs.getString("MRDMCG_SEX"));
			p.setDob(rs.getDate("MRDMCG_DOB"));
			p.setMarital_status(rs.getString("MRDMCG_MARITAL_STATUS"));
			p.setOccupation(rs.getString("MRDMCG_OCCUPATION"));
			p.setAadhaar_no(java.math.BigInteger.valueOf(rs.getLong("MRDMCG_AADHAAR_NO")));
			p.setAddress(rs.getString("MRDMCG_ADDRESS"));
			p.setArea_name(rs.getString("MRDMCG_AREA_NAME"));
			p.setCity_name(rs.getString("MRDMCG_CITY_NAME"));
			p.setState(rs.getString("MRDMCG_STATE"));
			p.setPincode(rs.getString("MRDMCG_PINCODE"));
			p.setPhone(java.math.BigInteger.valueOf(rs.getLong("MRDMCG_PHONE")));
			p.setMobile_no(java.math.BigInteger.valueOf(rs.getLong("MRDMCG_MOBILE_NO")));
			p.setMail_id(rs.getString("MRDMCG_MAIL_ID"));
			p.setDependent_type(rs.getString("MRDMCG_DEPENDENT_TYPE"));
			p.setDependent_name(rs.getString("MRDMCG_DEPENDENT_NAME"));
			p.setDependent_occupation(rs.getString("MRDMCG_DEPENDENT_OCCUPATION"));
			p.setDependent_aadhaar_no(java.math.BigInteger.valueOf(rs.getLong("MRDMCG_DEPENDENT_AADHAAR_NO")));
			p.setDiv_code(rs.getString("MRDMCG_DIV_CODE"));
		}
		return p;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		
	}
	public static void main(String args[]) {
		PatientProcedureCalls p = new PatientProcedureCalls();
		//p.callProcedure1();
		try{
		Patient res= p.login("O17123854","chloe100");
		System.out.println("function returned : "+res);
		
		}catch (ApplicationException e) {
			// TODO: handle exception
			logger.info(e.getMessage());
		}
	}

	
}
