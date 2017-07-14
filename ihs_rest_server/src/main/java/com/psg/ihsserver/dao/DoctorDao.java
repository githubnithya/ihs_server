package com.psg.ihsserver.dao;

import java.util.List;

import com.psg.ihsserver.entity.Doctor;

public interface DoctorDao {
	public List<Doctor> getDoctorForDepartment(Long dept_no);
	public List<Doctor> getAllDoctors();
}
