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
import com.psg.ihsserver.bean.DepartmentBean;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.exception.ApplicationException;

import oracle.jdbc.OracleTypes;

public class DepartmentProcedureCalls  {
	Connection dbConnection = null;
	CallableStatement callableStatement = null;
	
	private static final Logger logger = Logger.getLogger(DepartmentProcedureCalls.class);
	
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
	
	public List<Department> getAllDepartments() throws ApplicationException {
		String procedureCall = "{call LISTDEPTS(?)}";
		try{
		dbConnection = getDBConnection();
		callableStatement = dbConnection.prepareCall(procedureCall);
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.executeQuery();
		ResultSet rs = (ResultSet) callableStatement.getObject(1);
		ArrayList<Department> deptList = new ArrayList<>();
		while(rs.next()){
		Department dept= new Department();
		dept.setDept_name(rs.getString("MRDTCG_DEPT_NAME"));
		dept.setDept_no(rs.getLong("MRDTCG_CONS_DEPT"));
		deptList.add(dept);
		}
		if(deptList.isEmpty()){
			logger.info("List is empty");
			return null;
		}
		return deptList;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	}
	public static void main(String args[]) {
		DepartmentProcedureCalls dp= new DepartmentProcedureCalls();
		try {
			dp.getAllDepartments();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
