package com.psg.ihsserver.dao;

import java.sql.Date;
import java.util.List;

import com.psg.ihsserver.entity.Appointment;

public interface AppointmentDao {
	
	public boolean bookAppointment(Appointment appointment);
	public List<Appointment> getAllAppointmentsForPatient(String online_reg_no);
	public boolean cancelAppointment(String online_reg_no, Date app_date);
	public boolean updateTxId(String opCode, String date, String tx_id);

}
