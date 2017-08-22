package com.psg.ihsserver.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.psg.ihsserver.dao.PatientDao;
import com.psg.ihsserver.daoimpl.PatientDaoImpl;
import com.psg.ihsserver.entity.Patient;

public class DaoTest {
	
	public static void main(String args[])
	{
		Patient p = createNewPatientTest();
		PatientDao pDao = new PatientDaoImpl();
		try {
			pDao.insertNewPatient(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Patient createNewPatientTest()
	{
		Patient patient = new Patient();
		patient.setOnline_reg_no("I17000002");
		//patient.setOp_code("O17000001");
		patient.setPatient_name("Ronaldo");
		patient.setSex("M");
		String startDate="24-06-1987";
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date;
		try {
			date = sdf1.parse(startDate);
			java.sql.Date sqlDOB = new java.sql.Date(date.getTime());  
			
	//		patient.setDob(sqlDOB);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		patient.setMarital_status("M");
		patient.setOccupation("Football player");
		//Long aadhar_no = new Long(12345567891011);
	//	patient.setAadhaar_no(new BigInteger("345567891011"));
		patient.setAddress("Lionel Messi's House");
		patient.setArea_name("Castelldefels");
		patient.setCity_name("Castelldefels");
		patient.setState("Spain");
		patient.setPincode("641020");
	//	patient.setPhone(new BigInteger("04222692224"));
	//	patient.setMobile_no(new BigInteger("1234567892"));
		patient.setMail_id("lionelmessi@gmail.com");
		patient.setDependent_type("F");
		patient.setDependent_name("Jorge Hor�cio Messi");
		patient.setDependent_name("Father of player");
	//	patient.setDependent_aadhaar_no(new BigInteger("234567891213"));
		patient.setDiv_code("D");
		
		return patient;
	}

}
