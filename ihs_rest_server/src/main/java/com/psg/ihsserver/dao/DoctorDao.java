package com.psg.ihsserver.dao;

import java.util.List;

import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.exception.ApplicationException;

public interface DoctorDao {
	public List<Doctor> getDoctorForDepartment(String deptName) throws ApplicationException;
	public List<Doctor> getAllDoctors() throws ApplicationException;
}
