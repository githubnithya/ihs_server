package com.psg.ihsserver.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.psg.ihsserver.dao.DepartmentDao;
import com.psg.ihsserver.dao.DoctorDao;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.util.HibernateUtil;

import oracle.net.aso.l;

public class DepartmentDaoImpl implements DepartmentDao{

	DoctorDao docDao;
	SessionFactory sf;
	
	@Override
	public List<Department> getAllDepartments() {
		Session session = null;
		List<Department> listOfDepartments = null;
		sf = HibernateUtil.getSessionFactory();
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			listOfDepartments = session.createCriteria(Department.class).list();
			//TODO Added to remove LazyInitialization exception
			docDao = new DoctorDaoImpl();
			
			for(Department d : listOfDepartments)
				{
					List<Doctor> doctorList = new ArrayList<Doctor>();
					doctorList = docDao.getDoctorForDepartment(d.getDept_no());
					d.setDoctorsList(doctorList);
				}
			
			System.out.println("Size of department list "+ listOfDepartments.size());
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
	return listOfDepartments;
	}

}
