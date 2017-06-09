package com.psg.ihsserver.service;

import java.sql.Date;
import java.util.List;

import com.psg.ihsserver.dao.AppointmentDao;
import com.psg.ihsserver.daoimpl.AppointmentDaoImpl;
import com.psg.ihsserver.entity.Appointment;

public class AppointmentService {
	
	AppointmentDao appDao ;
	public boolean bookAppointment(Appointment appointment)
	{
		appDao = new AppointmentDaoImpl();
		return appDao.bookAppointment(appointment);
	}
	public List<Appointment> getAllAppointmentsForPatient(String online_reg_no)
	{
		appDao = new AppointmentDaoImpl();
		return appDao.getAllAppointmentsForPatient(online_reg_no);
	}
	public boolean cancelAppointment(String online_reg_no, Date app_date)
	{
		appDao = new AppointmentDaoImpl();
		return appDao.cancelAppointment(online_reg_no, app_date);
	}
}
