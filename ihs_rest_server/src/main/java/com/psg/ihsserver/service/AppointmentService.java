package com.psg.ihsserver.service;

import java.sql.Date;
import java.util.List;

import com.psg.ihsserver.bean.AppointmentBean;
import com.psg.ihsserver.dao.AppointmentDao;
import com.psg.ihsserver.daoimpl.AppointmentDaoImpl;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.Utils;

import oracle.net.aso.a;

public class AppointmentService {
	
	AppointmentDao appDao ;
	public boolean bookAppointment(Appointment appointment) throws ApplicationException
	{
		appDao = new AppointmentDaoImpl();
		return appDao.bookAppointment(appointment);
	}
	public List<Appointment> getAllAppointmentsForPatient(String online_reg_no) throws ApplicationException
	{
		appDao = new AppointmentDaoImpl();
		return appDao.getAllAppointmentsForPatient(online_reg_no);
	}
	public boolean cancelAppointment(String online_reg_no, Date app_date) throws ApplicationException
	{
		appDao = new AppointmentDaoImpl();
		return appDao.cancelAppointment(online_reg_no, app_date);
	}
	public boolean bookAppointment(AppointmentBean appointment) throws ApplicationException {
		
		appDao = new AppointmentDaoImpl();
		return appDao.bookAppointment(Utils.convertToEntity(appointment));
	}
	public boolean updateTxId(String opCode, String date, String tx_id) throws ApplicationException
	{
		appDao = new AppointmentDaoImpl();
		return appDao.updateTxId(opCode, date, tx_id);
	}
	
}
