package com.psg.ihsserver.dao;

import java.util.List;

import com.psg.ihsserver.entity.Doctor;

public interface DoctorDao {
	public List<Doctor> getDoctorForDepartment(String deptName);
	public List<Doctor> getAllDoctors();
}
