package com.psg.ihsserver.daoimpl;


import java.math.BigInteger;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


import com.psg.ihsserver.dao.PatientDao;

import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.*;

public class PatientDaoImpl implements PatientDao {

	private final String TAG = "PatientDaoImpl";
	private SessionFactory sf;
	private Utils util;
	private static final Logger logger = Logger.getLogger(PatientDaoImpl.class);
	@Override
	public Patient getPatient(String online_reg_no) throws ApplicationException {
		Session session = null;
		Patient patient = null;
		sf = HibernateUtil.getSessionFactory();
		
		try
		{
			if(online_reg_no==null)
				throw new ApplicationException("No valid reg no");
			session = sf.openSession();
			session.beginTransaction();
			patient = (Patient) session.get(Patient.class, online_reg_no);
			
		}
		catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		
		finally
		{
			session.close();
		}
		return patient;
	}

	
	@Override
	public Patient getPatientByOpCode(String op_code) throws ApplicationException
	{
		Session session = null;
		Patient patientByOpcode = null;
		sf = HibernateUtil.getSessionFactory();
		
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Patient.class);
			patientByOpcode = (Patient) criteria.add(Restrictions.eq("op_code", op_code))
												.uniqueResult();
			
			session.getTransaction().commit();
		}
		catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		finally
		{
			session.close();
		}
		return patientByOpcode;
	}
	
	
	@Override
	public String isPatient(String op_code) throws ApplicationException
	{
		String mobile_no = null;
		Session session = null;
		Patient patientByOpcode = null;
		sf = HibernateUtil.getSessionFactory();
		
		System.out.println("op_code "+ op_code);
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Patient.class);
			patientByOpcode = (Patient) criteria.add(Restrictions.eq("op_code", op_code).ignoreCase())
												.uniqueResult();
			if(null != patientByOpcode)
				{
					logger.debug(TAG + "In isPatient - Patient Active " + patientByOpcode.getPatient_name());
					mobile_no = patientByOpcode.getMobile_no().toString();
					System.out.println("dao: "+mobile_no);
				}
			session.getTransaction().commit();
		}
		catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		finally
		{
			session.close();
		}
		return mobile_no;
	}
	@Override
	public boolean insertNewPatient(Patient patient) throws ApplicationException
	{
			
			Session session = null;
			sf = HibernateUtil.getSessionFactory();
			try
			{
				session = sf.openSession();
				session.beginTransaction();
				String id = (String) session.save(patient);
				session.getTransaction().commit();
			}catch(Exception e){
				logger.info(e.getMessage());
				throw new ApplicationException(e.getMessage());
			}
			finally
			{
				session.close();
			}
		return false;
	}

	@Override
	public boolean updatePatient(Patient patient) throws ApplicationException{
		Session session = null;
		sf = HibernateUtil.getSessionFactory();
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			session.update(patient);
			session.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		
		finally
		{
			session.close();
		}
	return false;
	}

	@Override
	public boolean deletePatient(Patient patient) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Patient getPatientByDetails(String patient_name, String dob, String mobile_no)throws ApplicationException {
		Session session = null;
		Patient patientByDetails = null;
		if(logger.isDebugEnabled()) 
			System.out.println("getPatientByDetails - Name = "+ patient_name + " D0B = " + dob + " Mobile No " + mobile_no
					+ "\n" + new BigInteger(mobile_no).longValue());
		
		sf = HibernateUtil.getSessionFactory();
		
		java.sql.Date sql_dob = Utils.generateSQLDate(dob);
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Patient.class);
			patientByDetails = (Patient) criteria.add(Restrictions.eq("patient_name", patient_name).ignoreCase())
					.add(Restrictions.eq("dob", sql_dob))
					.add(Restrictions.eq("mobile_no", new BigInteger(mobile_no)))
					.uniqueResult();
			System.out.println("In DAO " + patientByDetails.getPatient_name());
			session.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
	
		finally
		{
			session.close();
		}
		return patientByDetails;
	}

	@Override
	public String forgotOpCode(String patient_name, String dob, String mobile_no) throws ApplicationException {
		Session session = null;
		Patient patientByDetails = null;
		String op_code = null;
		//if(logger.isDebugEnabled()) 
		System.out.println("getPatientByDetails - Name = "+ patient_name + " D0B = " + dob + " Mobile No " + mobile_no
					+ "\n" + mobile_no);
		
		sf = HibernateUtil.getSessionFactory();
		
		java.sql.Date sql_dob = Utils.generateSQLDate(dob);
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Patient.class);
			patientByDetails = (Patient) criteria.add(Restrictions.eq("patient_name", patient_name).ignoreCase())
					.add(Restrictions.eq("dob", sql_dob))
					.add(Restrictions.eq("mobile_no", new BigInteger(mobile_no)))
					.uniqueResult();
			if(null != patientByDetails)
				{
				op_code = patientByDetails.getOp_code();
				logger.debug(TAG + "In forgotOpCode - " + op_code);
				}
			
			session.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		
		finally
		{
			session.close();
		}
		return op_code;
	}
	
	@Override
	public Patient login(String op_code, String password) throws ApplicationException
	{
		Session session = null;
		Patient patientLogin = null;
		sf = HibernateUtil.getSessionFactory();
		
		System.out.println("op_code "+ op_code);
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Patient.class);
			patientLogin = (Patient) criteria.add(Restrictions.eq("op_code", op_code).ignoreCase())
					 						.add(Restrictions.eq("patient_pwd", password))
												.uniqueResult();
			
			System.out.println("dao: "+patientLogin);
			session.getTransaction().commit();
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		finally
		{
			session.close();
		}
		return patientLogin;
	}


	@Override
	public Boolean updateNewP(String op_code, String password) throws ApplicationException{
		
		Session session = null;
		sf = HibernateUtil.getSessionFactory();
		Patient updateNewP = null;
		Boolean updateResult = false;
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Patient.class);
			updateNewP = (Patient) criteria.add(Restrictions.eq("op_code", op_code).ignoreCase())
					 						.uniqueResult();
			
			updateNewP.setPatient_pwd(password);
			
			session.update(updateNewP);
			session.getTransaction().commit();
			updateResult = true;
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		
		finally
		{
			session.close();
		}
		return updateResult;
	}
	
	public String getPassword(String op_code) throws ApplicationException
	{
		String pwd=null;
		Session session = null;
		Patient patientByOpcode = null;
		sf = HibernateUtil.getSessionFactory();
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Patient.class);
			patientByOpcode = (Patient) criteria.add(Restrictions.eq("op_code", op_code))
												.uniqueResult();
			
			session.getTransaction().commit();
			pwd= patientByOpcode.getPatient_pwd();
			 pwd= Utils.decrypt(pwd);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		}
		finally
		{
			session.close();
		}
		
		return pwd;
	}
}
