package com.psg.ihsserver.servicejdbc;

import java.util.ArrayList;
import java.util.List;

import com.psg.ihsserver.bean.DoctorBean;
import com.psg.ihsserver.daojdbc.DoctorProcedureCalls;
import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.Utils;

public class DoctorServiceJdbc {

	DoctorProcedureCalls docDao;
	public List<DoctorBean> getDoctorForDepartment(Integer deptNo) throws ApplicationException
	{
		docDao= new DoctorProcedureCalls();
		List<Doctor> docList =docDao.getAllDoctorsForDept(deptNo);
		List<DoctorBean> docBeanList = new ArrayList<DoctorBean>();
		if(docList!=null)
		for(Doctor doc: docList)
		{
			docBeanList.add(Utils.convertToBean(doc));
		}
		
		return docBeanList;
	}
	
	
}
