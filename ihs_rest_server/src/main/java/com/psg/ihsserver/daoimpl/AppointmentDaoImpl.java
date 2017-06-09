package com.psg.ihsserver.daoimpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.psg.ihsserver.dao.AppointmentDao;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.util.HibernateUtil;

public class AppointmentDaoImpl implements AppointmentDao{

	SessionFactory sf;
	
	@Override
	public boolean bookAppointment(Appointment appointment) {
		Session session = null;
		sf = HibernateUtil.getSessionFactory();
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			String id = (String) session.save(appointment);
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
	public List<Appointment> getAllAppointmentsForPatient(String online_reg_no) {
		Session session = null;
		List<Appointment> listOfAppointments = null;
		sf = HibernateUtil.getSessionFactory();
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			listOfAppointments = session.createCriteria(Appointment.class).list();
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
	return listOfAppointments;
	}

	@Override
	public boolean cancelAppointment(String online_reg_no, Date app_date) {
		
		Session session = null;
		Appointment appointment = null;
		sf = HibernateUtil.getSessionFactory();
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Appointment.class);
			appointment = (Appointment) criteria.add(Restrictions.eq("online_reg_no", online_reg_no))
												.add(Restrictions.eq("app_date", app_date))
												.uniqueResult();
			//TODO Check if Div Code is to be updated to C for cancellation
			if(null != appointment)
				appointment.setDiv_code("C");
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
		return true;
	}

}
