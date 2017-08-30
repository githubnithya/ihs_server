package com.psg.ihsserver.daojdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.psg.ihsserver.BackgroundJobManager;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.exception.ApplicationException;

import oracle.jdbc.OracleTypes;

public class DoctorProcedureCalls {
	Connection dbConnection = null;
	CallableStatement callableStatement = null;
	
	private static final Logger logger = Logger.getLogger(DoctorProcedureCalls.class);
	
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
	
	public List<Doctor> getAllDoctorsForDept(Integer deptNo) throws ApplicationException {
		String procedureCall = "{call LISTDOC(?,?)}";
		try{
		dbConnection = getDBConnection();
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.setInt(1, deptNo);
		callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		callableStatement.executeQuery();
		ResultSet rs = (ResultSet) callableStatement.getObject(2);
		ArrayList<Doctor> docList = new ArrayList<>();
		while(rs.next()){
		Doctor doctor= new Doctor();
		doctor.setDoc_name(rs.getString("MRDMCG_DOC_NAME"));
		doctor.setDoc_availability(rs.getString("MRDMCG_DOC_A"));
		doctor.setDoc_fee(rs.getLong("MRDMCG_DOC_FEE"));
		doctor.setDoc_name(rs.getString("MRDMCG_DOC_NAME"));
		doctor.setDoc_no(rs.getLong("MRDMCG_CONS_DR_NO"));
		docList.add(doctor);
		}
		if(docList.isEmpty()){
			logger.info("List is empty");
			return null;
		}
		return docList;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	public static void main(String args[]) {
		DoctorProcedureCalls dp= new DoctorProcedureCalls();
		try {
			dp.getAllDoctorsForDept(1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
