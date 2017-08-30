package com.psg.ihsserver.servicejdbc;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.psg.ihsserver.bean.AppointmentBean;
import com.psg.ihsserver.daojdbc.AppointmentProcedureCalls;
import com.psg.ihsserver.daojdbc.PaymentTransactionProcedureCalls;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.Utils;


public class AppointmentServiceJdbc {
	
	AppointmentProcedureCalls appDao  ;
	PaymentTransactionProcedureCalls payDao;
	public boolean bookAppointment(AppointmentBean appointmentBean) throws ApplicationException
	{
		appDao =new AppointmentProcedureCalls();
		Appointment appointment= Utils.convertToEntity(appointmentBean);
		return appDao.bookAppointment(appointment);
	}
	public List<AppointmentBean> getAllAppointmentsForPatient(String online_reg_no) throws ApplicationException
	{
		appDao =new AppointmentProcedureCalls();
		List<Appointment> appointmentList= appDao.getAllAppointments(online_reg_no);
		List<AppointmentBean> appointmentBeanList=new ArrayList<>();
		for(Appointment a: appointmentList){
			AppointmentBean ab= Utils.convertToBean(a);
			appointmentBeanList.add(ab);
		}
		return appointmentBeanList;
	}
	public boolean cancelAppointment(String online_reg_no, String app_date) throws ApplicationException
	{
		appDao =new AppointmentProcedureCalls();
		SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		Date sqlDate=null;
		try {
			 sqlDate= new Date(df.parse(app_date).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException(e.getMessage());
		}
		return appDao.cancelAppointment(online_reg_no, sqlDate);
	}
	
	public boolean storeTxId(String opCode, String date, String tx_id) throws ApplicationException
	{
		payDao= new PaymentTransactionProcedureCalls();
		SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
		Date sqlDate=null;
		try {
			 sqlDate= new Date(df.parse(date).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException(e.getMessage());
		}
		return payDao.storeTransaction(opCode, sqlDate, tx_id);
	}
	
}
