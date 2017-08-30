package com.psg.ihsserver.daojdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.psg.ihsserver.BackgroundJobManager;
import com.psg.ihsserver.bean.AppointmentBean;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.exception.ApplicationException;

import oracle.jdbc.OracleTypes;

public class AppointmentProcedureCalls {
	Connection dbConnection = null;
	CallableStatement callableStatement = null;
	
	private static final Logger logger = Logger.getLogger(AppointmentProcedureCalls.class);
	
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
	
	public Boolean bookAppointment(Appointment apt) throws ApplicationException {
		int resultOnProcedureCall=0;
		String mobile=null;
		String procedureCall = "{call bookAppointment(?,?,?,?)}";
		try{
		dbConnection = getDBConnection();
		System.out.println("got connection");
		System.out.println("appointment: "+apt);
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setLong(1, apt.getApp_id());
		callableStatement.setString(2, apt.getOp_code());
		Date sqldate = new Date((apt.getApp_date()).getTime());
		callableStatement.setDate(3, sqldate);
		callableStatement.setInt(4, apt.getCharge());
		
		resultOnProcedureCall=callableStatement.executeUpdate();
		logger.info("Result on procedure call: "+resultOnProcedureCall);
		return true;
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}

	public List<Appointment> getAllAppointments(String opCode) throws ApplicationException{
		int resultOnProcedureCall=0;
		String procedureCall = "{call LISTAPPOINTMENT(?,?)}";
		
		try{
		dbConnection = getDBConnection();
		System.out.println("got connection");
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setString(1, opCode);
		callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		callableStatement.executeQuery();
		ResultSet rs = (ResultSet) callableStatement.getObject(2);
		ArrayList<Appointment> patientAppointments = new ArrayList<>();
		while(rs.next()){
			Appointment apt= new Appointment();
			apt.setApp_id(rs.getInt("MRDTCG_APP_ID"));
			apt.setOnline_reg_no(rs.getString("MRDMCG_ONLINE_REG_NO"));
			apt.setOp_code(rs.getString("MRDTCG_OP_CODE"));
			apt.setCons_dept(rs.getInt("MRDTCG_CONS_DEPT"));
			apt.setApp_date(rs.getDate("MRDTCG_APP_DATE"));
			apt.setCons_dr_no(rs.getInt("MRDTCG_CONS_DR_NO"));
			apt.setSer_code(rs.getString("MRDTCG_SER_CODE"));
			apt.setReceipt_code(rs.getString("MRDTCG_RECEIPT_CODE"));
			apt.setReceipt_status(rs.getString("MRDTCG_RECEIPT_STATUS"));
			apt.setCharge(rs.getInt("MRDTCG_CHARGE"));
			apt.setBill_code(rs.getString("MRDTCG_BILL_CODE"));
			apt.setVisit_status(rs.getString("MRDMCG_VISIT_STATUS"));
			apt.setApp_status(rs.getString("MRDTCG_APP_STATUS"));
			apt.setDiv_code(rs.getString("MRDTCG_DIV_CODE"));
			apt.setTx_statusMsg(rs.getString("MRDTCG_STATUSMSG"));
			apt.setTx_statusCode(rs.getString("MRDTCG_STATUSCODE"));
			apt.setTx_amount(rs.getString("MRDTCG_AMOUNT"));
			apt.setTx_dateTime(rs.getString("MRDTCG_DATETIME"));
			apt.setTx_merchantTxId(rs.getString("MRDTCG_MERCHANTID"));
			apt.setTx_merchantCode(rs.getString("MRDTCG_MERCHANTCODE"));
			apt.setTx_paymentMethod(rs.getString("MRDTCG_PAYMENTMETHOD"));
			apt.setTx_pg_id(rs.getString("MRDTCG_PG_ID"));
			apt.setTx_refundId(rs.getString("MRDTCG_REFUNDID"));
			apt.setTx_checkoutObj(rs.getString("MRDTCG_CHECKOUTOBJ"));
			patientAppointments.add(apt);
			System.out.println("added to list : "+apt);
		}
		return patientAppointments;
		}catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
	}
	
	public boolean cancelAppointment(String opCode, Date appDate) throws ApplicationException {
		int resultOnProcedureCall=0;
		String procedureCall = "{call CANCELAPPOINTMENT(?,?)}";
		try{
		dbConnection = getDBConnection();
		System.out.println("got connection");
		
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setString(1, opCode);
		callableStatement.setDate(2, appDate);
		
		resultOnProcedureCall=callableStatement.executeUpdate();
		logger.info("Result on procedure call: "+resultOnProcedureCall);
		return true;
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}
	public static void main(String args[]) {
		AppointmentProcedureCalls ap= new AppointmentProcedureCalls();
		try{
		Appointment apt= new Appointment();
		DateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date d=df.parse("22-03-2015");
		Date sqldate = new Date(d.getTime());
		apt.setApp_date(sqldate);
		apt.setCharge(100);
		apt.setOp_code("O17123877");
		apt.setApp_id(22);
		//Boolean b=ap.bookAppointment(apt);
		//ap.getAllAppointments("O17123456");
		d=df.parse("12-08-2017");
		//boolean res=ap.cancelAppointment("O17123456", d);
		System.out.println("booked.");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
