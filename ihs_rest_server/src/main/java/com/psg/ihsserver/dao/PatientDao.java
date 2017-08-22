package com.psg.ihsserver.dao;


import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.exception.ApplicationException;

public interface PatientDao {

	public Patient getPatient(String online_reg_no) throws ApplicationException;
	public Patient getPatientByOpCode(String op_code) throws ApplicationException;
	public String isPatient(String op_code) throws ApplicationException;
	public boolean insertNewPatient(Patient patient) throws ApplicationException;
	public boolean updatePatient(Patient patient) throws ApplicationException;
	public boolean deletePatient(Patient patient);
	public Patient getPatientByDetails(String patient_name, String dob, String mobile_no) throws ApplicationException;
	public String forgotOpCode(String patient_name, String dob, String mobile_no) throws ApplicationException;
	public Patient login(String op_code, String password) throws ApplicationException;
	public Boolean updateNewP(String op_code, String password) throws ApplicationException;
	
}
