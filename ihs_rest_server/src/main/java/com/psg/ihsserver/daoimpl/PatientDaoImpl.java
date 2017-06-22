package com.psg.ihsserver.daoimpl;


import java.math.BigInteger;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


import com.psg.ihsserver.dao.PatientDao;

import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.util.*;

public class PatientDaoImpl implements PatientDao {

	private SessionFactory sf;
	private Utils util;
	private static final Logger logger = Logger.getLogger(PatientDaoImpl.class);
	
	@Override
	public Patient getPatient(String online_reg_no) {
		Session session = null;
		Patient patient = null;
		sf = HibernateUtil.getSessionFactory();
		
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			patient = (Patient) session.get(Patient.class, online_reg_no);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return patient;
	}

	
	@Override
	public Patient getPatientByOpCode(String op_code) 
	{
		Session session = null;
		Patient patientByOpcode = null;
		sf = HibernateUtil.getSessionFactory();
		
		System.out.println("op_code "+ op_code);
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Patient.class);
			patientByOpcode = (Patient) criteria.add(Restrictions.eq("op_code", op_code))
												.uniqueResult();
			
			System.out.println("In DAO " + patientByOpcode.getPatient_name());
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return patientByOpcode;
	}
	
	@Override
	public boolean insertNewPatient(Patient patient) 
	{
			
			Session session = null;
			sf = HibernateUtil.getSessionFactory();
			try
			{
				session = sf.openSession();
				session.beginTransaction();
				String id = (String) session.save(patient);
				session.getTransaction().commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				session.close();
			}
		return false;
	}

	@Override
	public boolean updatePatient(Patient patient) {
		Session session = null;
		sf = HibernateUtil.getSessionFactory();
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			session.update(patient);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
	public Patient getPatientByDetails(String patient_name, String dob, String mobile_no) {
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
			patientByDetails = (Patient) criteria.add(Restrictions.eq("patient_name", patient_name))
					.add(Restrictions.eq("dob", sql_dob))
					.add(Restrictions.eq("mobile_no", new BigInteger(mobile_no)))
					.uniqueResult();
			System.out.println("In DAO " + patientByDetails.getPatient_name());
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return patientByDetails;
	}

}
