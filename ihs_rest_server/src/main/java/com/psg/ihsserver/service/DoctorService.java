package com.psg.ihsserver.service;

import java.util.ArrayList;
import java.util.List;

import com.psg.ihsserver.bean.DoctorBean;
import com.psg.ihsserver.dao.DoctorDao;
import com.psg.ihsserver.daoimpl.DoctorDaoImpl;
import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.util.Utils;

public class DoctorService {

	DoctorDao docDao;
	public List<Doctor> getDoctorForDepartment(Long dept_no)
	{
		docDao = new DoctorDaoImpl();
		return docDao.getDoctorForDepartment(dept_no);
	}
	
	public List<DoctorBean> getAllDoctors()
	{
		docDao = new DoctorDaoImpl();
		List<Doctor> docList = docDao.getAllDoctors();
		List<DoctorBean> docBeanList = new ArrayList<DoctorBean>();
		for(Doctor doc: docList)
		{
			docBeanList.add(Utils.convertToBean(doc));
		}
		
		return docBeanList;
	}
}
