package com.psg.ihsserver.service;

import java.util.ArrayList;
import java.util.List;

import com.psg.ihsserver.bean.DepartmentBean;
import com.psg.ihsserver.dao.DepartmentDao;
import com.psg.ihsserver.daoimpl.DepartmentDaoImpl;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.Utils;

public class DepartmentService {

	DepartmentDao deptDao;

	public List<DepartmentBean> getAllDepartments () throws ApplicationException
	{
		deptDao = new DepartmentDaoImpl();
		List<Department> departments = deptDao.getAllDepartments();
		
		List<DepartmentBean> deptBeanList = new ArrayList<>();
		
		for(Department department: departments)
		{
			deptBeanList.add(Utils.convertToBean(department));
		}
		
		return deptBeanList;
	}
}
