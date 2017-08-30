package com.psg.ihsserver.servicejdbc;

import com.psg.ihsserver.bean.PatientBean;
import com.psg.ihsserver.daojdbc.PatientProcedureCalls;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.Utils;


public class PatientServiceJdbc {
	
	PatientProcedureCalls patientDao;
	
	
	public PatientBean getPatientByOpCode(String op_code) throws ApplicationException
	{
		patientDao= new PatientProcedureCalls();
		Patient p= patientDao.getPatientByOpCode(op_code);
		PatientBean patientBean= Utils.convertToBean(p);
		return patientBean;
	}
	
	public String isPatient(String op_code) throws ApplicationException
	{
		patientDao= new PatientProcedureCalls();
		return patientDao.isPatient(op_code);
	}
	public String forgotOpCode(String patient_name, String dob, String mobile_no) throws ApplicationException
	{
		patientDao= new PatientProcedureCalls();
		return patientDao.forgotOpCode(patient_name, dob, mobile_no);
	}
	
	public Boolean forgotPwd(String opCode) throws ApplicationException
	{
		patientDao= new PatientProcedureCalls();
		String mobileNo = patientDao.isPatient(opCode);
		if(null != mobileNo && Utils.mobileNoValidator(mobileNo))
		{
			//TODO Send OTP by SMS
			return true;
		}
		else
			return false;
	}
	
	/*public boolean insertNewPatient(Patient patient) throws ApplicationException
	{
		
	}*/
	public boolean updatePatient(PatientBean patient) throws ApplicationException
	{
		patientDao= new PatientProcedureCalls();
		String encPassword = Utils.encrypt(patient.getPatient_pwd());
		return patientDao.changePassword(patient.getOp_code(), encPassword);
	}
	
	/*public Patient getPatientByDetails(String patient_name, String dob, String mobile_no) throws ApplicationException {
		return patientDao.getPatientByDetails(patient_name, dob, mobile_no);
	}*/
	public Patient login(String op_code, String password) throws ApplicationException
	{
		patientDao= new PatientProcedureCalls();
		return patientDao.login(op_code, password);
	}
	
}
