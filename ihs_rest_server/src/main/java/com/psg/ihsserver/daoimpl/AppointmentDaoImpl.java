package com.psg.ihsserver.daoimpl;

import java.sql.Date;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.psg.ihsserver.dao.AppointmentDao;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.util.HibernateUtil;
import com.psg.ihsserver.util.Utils;

import oracle.net.aso.q;

public class AppointmentDaoImpl implements AppointmentDao{

	SessionFactory sf;
	
	@Override
	public boolean bookAppointment(Appointment appointment) {
		Session session = null;
		sf = HibernateUtil.getSessionFactory();
		boolean insertStatus = false;
		try
		{
			System.out.println("appDate before insert " +appointment.getApp_date());
			session = sf.openSession();
			session.beginTransaction();
			int id = (int) session.save(appointment);
			session.getTransaction().commit();
			if(id > 0)
				insertStatus = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	return insertStatus;
		
	}

	@SuppressWarnings("unchecked")
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

	public boolean updateTxId(String opCode, String date, String tx_id)
	{
		Session session = null;
		boolean response = false;
		sf = HibernateUtil.getSessionFactory();
		System.out.println("Inserting tx_id " + opCode + "  " + date + " " + tx_id);
		try{
			session = sf.openSession();
			session.beginTransaction();
			
			Query query = session.createSQLQuery("INSERT INTO PATIENT_PAYMENT_TX(MRDMCG_OP_CODE, MRDMCG_TX_DATE, MRDMCG_TX_ID) "
					+ "VALUES ('" + opCode + "',to_date('" + Utils.generateSQLDate(date) + "','yyyy-MM-dd'),'" + tx_id
					+ "')" );
			
			
//			query.setParameter(1, opCode);
//			query.setParameter(2, Utils.generateSQLDate(date));
//			query.setParameter(3, tx_id);
			int result = query.executeUpdate();
			session.getTransaction().commit();
			
			if(result > 0)
				response = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			session.close();
			
		}
		return response;
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
