package com.psg.ihsserver.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.psg.ihsserver.dao.DoctorDao;
import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.util.HibernateUtil;

public class DoctorDaoImpl implements DoctorDao{

	SessionFactory sf;
	@SuppressWarnings("unchecked")
	@Override
	public List<Doctor> getDoctorForDepartment(Long dept_no) {
		
		Session session = null;
		List<Doctor> docList = null;
		sf = HibernateUtil.getSessionFactory();
		
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Doctor.class);
			criteria.add(Restrictions.eq("department",dept_no));
			docList = criteria.list();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return docList;
	}
	

}
