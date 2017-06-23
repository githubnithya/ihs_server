package com.psg.ihsserver.dao;


import com.psg.ihsserver.entity.Patient;

public interface PatientDao {

	public Patient getPatient(String online_reg_no);
	public Patient getPatientByOpCode(String op_code);
	public String isPatient(String op_code);
	public boolean insertNewPatient(Patient patient);
	public boolean updatePatient(Patient patient);
	public boolean deletePatient(Patient patient);
	public Patient getPatientByDetails(String patient_name, String dob, String mobile_no);
	public String forgotOpCode(String patient_name, String dob, String mobile_no);
	
}
