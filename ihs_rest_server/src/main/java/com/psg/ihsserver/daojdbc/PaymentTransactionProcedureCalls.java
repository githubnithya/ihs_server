package com.psg.ihsserver.daojdbc;

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

import org.apache.log4j.Logger;

import com.psg.ihsserver.BackgroundJobManager;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.exception.ApplicationException;

import oracle.jdbc.OracleTypes;

public class PaymentTransactionProcedureCalls {
	Connection dbConnection = null;
	CallableStatement callableStatement = null;
	
	private static final Logger logger = Logger.getLogger(PaymentTransactionProcedureCalls.class);
	
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
	public boolean storeTransaction(String opCode, Date txDate, String txId) throws ApplicationException {
		int resultOnProcedureCall=0;
		String procedureCall = "{call STORETX(?,?,?)}";
		try{
		dbConnection = getDBConnection();
		System.out.println("got connection");
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setString(1, opCode);
		callableStatement.setDate(2, txDate);
		callableStatement.setString(3, txId);
		resultOnProcedureCall=callableStatement.executeUpdate();
		logger.info("Result on procedure call: "+resultOnProcedureCall);
		return true;
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
	}
	
	public List<Appointment> getAllAppointments(String opCode){
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
		
		
		}
		return patientAppointments;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String args[]) {
		PaymentTransactionProcedureCalls pt= new PaymentTransactionProcedureCalls();
		try{
		DateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date d= df.parse("22-10-16");
		//pt.storeTransaction("O17123478", d, "123528296723");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
