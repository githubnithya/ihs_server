package com.psg.ihsserver.dao;

import java.sql.Date;
import java.util.List;

import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.exception.ApplicationException;

public interface AppointmentDao {
	
	public boolean bookAppointment(Appointment appointment) throws ApplicationException;
	public List<Appointment> getAllAppointmentsForPatient(String online_reg_no) throws ApplicationException;
	public boolean cancelAppointment(String online_reg_no, Date app_date) throws ApplicationException;
	public boolean updateTxId(String opCode, String date, String tx_id) throws ApplicationException;

}
