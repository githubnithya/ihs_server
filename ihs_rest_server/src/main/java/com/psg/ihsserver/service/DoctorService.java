package com.psg.ihsserver.service;

import java.util.List;

import com.psg.ihsserver.dao.DoctorDao;
import com.psg.ihsserver.daoimpl.DoctorDaoImpl;
import com.psg.ihsserver.entity.Doctor;

public class DoctorService {

	DoctorDao docDao;
	public List<Doctor> getDoctorForDepartment(Long dept_no)
	{
		docDao = new DoctorDaoImpl();
		return docDao.getDoctorForDepartment(dept_no);
	}
}
