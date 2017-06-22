package com.psg.ihsserver.service;



import com.psg.ihsserver.dao.PatientDao;
import com.psg.ihsserver.daoimpl.PatientDaoImpl;
import com.psg.ihsserver.entity.Patient;


public class PatientService {
	
	PatientDao pDao;
	
	public Patient getPatient(String online_reg_no)
	{
		pDao = new PatientDaoImpl();
		return pDao.getPatient(online_reg_no);
	}
	public Patient getPatientByOpCode(String op_code)
	{
		pDao = new PatientDaoImpl();
		return pDao.getPatientByOpCode(op_code);
	}
	
	public boolean insertNewPatient(Patient patient)
	{
		pDao = new PatientDaoImpl();
		return pDao.insertNewPatient(patient);
	}
	public boolean updatePatient(Patient patient)
	{
		pDao = new PatientDaoImpl();
		return pDao.updatePatient(patient);
	}
	public boolean deletePatient(Patient patient)
	{
		pDao = new PatientDaoImpl();
		return pDao.deletePatient(patient);
	}
	public Patient getPatientByDetails(String patient_name, String dob, String mobile_no) {
		pDao = new PatientDaoImpl();
		return pDao.getPatientByDetails(patient_name, dob, mobile_no);
	}
}
