package com.psg.ihsserver.service;

import com.psg.ihsserver.dao.PatientDao;
import com.psg.ihsserver.daoimpl.PatientDaoImpl;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.Utils;


public class PatientService {
	
	PatientDao pDao;
	
	public Patient getPatient(String online_reg_no) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		return pDao.getPatient(online_reg_no);
	}
	public Patient getPatientByOpCode(String op_code) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		return pDao.getPatientByOpCode(op_code);
	}
	
	public String isPatient(String op_code) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		return pDao.isPatient(op_code);
	}
	public String forgotOpCode(String patient_name, String dob, String mobile_no) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		return pDao.forgotOpCode(patient_name, dob, mobile_no);
	}
	
	public Boolean forgotPwd(String opCode) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		String mobileNo = pDao.isPatient(opCode);
		if(null != mobileNo && Utils.mobileNoValidator(mobileNo))
		{
			//TODO Send OTP by SMS
			return true;
		}
		else
			return false;
	}
	
	public boolean insertNewPatient(Patient patient) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		return pDao.insertNewPatient(patient);
	}
	public boolean updatePatient(Patient patient) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		return pDao.updatePatient(patient);
	}
	public boolean deletePatient(Patient patient)
	{
		pDao = new PatientDaoImpl();
		return pDao.deletePatient(patient);
	}
	public Patient getPatientByDetails(String patient_name, String dob, String mobile_no) throws ApplicationException {
		pDao = new PatientDaoImpl();
		return pDao.getPatientByDetails(patient_name, dob, mobile_no);
	}
	public Patient login(String op_code, String password) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		return pDao.login(op_code, password);
	}
	public Boolean updateNewP(String op_code, String password) throws ApplicationException
	{
		pDao = new PatientDaoImpl();
		return pDao.updateNewP(op_code, password);
	}
}
