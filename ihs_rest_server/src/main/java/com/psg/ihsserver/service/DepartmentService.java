package com.psg.ihsserver.service;

import java.util.List;

import com.psg.ihsserver.dao.DepartmentDao;
import com.psg.ihsserver.daoimpl.DepartmentDaoImpl;
import com.psg.ihsserver.entity.Department;

public class DepartmentService {

	DepartmentDao deptDao;
	
	
	public List<Department> getAllDepartments()
	{
		deptDao = new DepartmentDaoImpl();
		return deptDao.getAllDepartments();
	}
}
