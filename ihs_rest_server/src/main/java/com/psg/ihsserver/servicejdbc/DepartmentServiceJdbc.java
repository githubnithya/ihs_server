package com.psg.ihsserver.servicejdbc;

import java.util.ArrayList;
import java.util.List;

import com.psg.ihsserver.bean.DepartmentBean;
import com.psg.ihsserver.daojdbc.DepartmentProcedureCalls;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.util.Utils;

public class DepartmentServiceJdbc {

	DepartmentProcedureCalls deptDao;

	public List<DepartmentBean> getAllDepartments () throws ApplicationException
	{
		deptDao= new DepartmentProcedureCalls();
		List<Department> departments = deptDao.getAllDepartments();
		
		List<DepartmentBean> deptBeanList = new ArrayList<>();
		
		for(Department department: departments)
		{
			deptBeanList.add(Utils.convertToBean(department));
		}
		
		return deptBeanList;
	}
}
