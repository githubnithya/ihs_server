package com.psg.ihsserver.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.psg.ihsserver.dao.PatientDao;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.util.*;

public class PatientDaoImpl implements PatientDao {

	SessionFactory sf;
	
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

}
