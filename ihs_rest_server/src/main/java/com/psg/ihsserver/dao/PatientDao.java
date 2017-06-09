package com.psg.ihsserver.dao;

import com.psg.ihsserver.entity.Patient;

public interface PatientDao {

	public Patient getPatient(String online_reg_no);
	public boolean insertNewPatient(Patient patient);
	public boolean updatePatient(Patient patient);
	public boolean deletePatient(Patient patient);
	
	
}
