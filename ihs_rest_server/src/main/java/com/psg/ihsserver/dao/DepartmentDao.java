package com.psg.ihsserver.dao;

import java.util.List;

import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.exception.ApplicationException;



public interface DepartmentDao {

	
	public List<Department> getAllDepartments() throws ApplicationException;
}
