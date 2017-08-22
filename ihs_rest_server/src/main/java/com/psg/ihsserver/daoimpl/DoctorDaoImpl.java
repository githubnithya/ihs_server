package com.psg.ihsserver.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.psg.ihsserver.dao.DoctorDao;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.HibernateUtil;

public class DoctorDaoImpl implements DoctorDao{

	private static final Logger logger = Logger.getLogger(DoctorDaoImpl.class);
	SessionFactory sf;
	@SuppressWarnings("unchecked")
	@Override
	public List<Doctor> getDoctorForDepartment(String deptName) throws ApplicationException {
		
		Session session = null;
		List<Doctor> docList = null;
		sf = HibernateUtil.getSessionFactory();
		
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			
			Criteria deptCriteria = session.createCriteria(Department.class);
			deptCriteria.add(Restrictions.eq("dept_name", deptName));
			Department dept = (Department) deptCriteria.uniqueResult();
			
			System.out.println("getDocForDept - DaoImpl" + dept.getDept_no());
			 
			Criteria criteria = session.createCriteria(Doctor.class);
			criteria.add(Restrictions.eq("department.dept_no",dept.getDept_no()));
			docList = criteria.list();
			
			System.out.println("getDocForDept - DaoImpl " + docList.size());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			throw new ApplicationException("Cannot get list of doctors");
		}
		finally
		{
			session.close();
		}
		return docList;
	}
	
	@Override
	public List<Doctor> getAllDoctors() throws ApplicationException {
		Session session = null;
		List<Doctor> docList = null;
		sf = HibernateUtil.getSessionFactory();
		
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Doctor.class);
			
			docList = criteria.list();
			
			for(Doctor doc: docList)
			{
				System.out.println("doc's dept " + doc.getDepartment().getDept_no());
			}
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			throw new ApplicationException("Cannot get list of doctors");
		}
		finally
		{
			session.close();
		}
		return docList;
	}
	

}
