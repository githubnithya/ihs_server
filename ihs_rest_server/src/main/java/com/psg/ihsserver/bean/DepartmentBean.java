package com.psg.ihsserver.bean;

import java.io.Serializable;
import java.util.List;



public class DepartmentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dept_no;

	private String dept_name;
	
	
	private List<DoctorBean> doctorsList;
	
	public DepartmentBean(){
		
	}
	public DepartmentBean(Long dept_no, String dept_name)
	{
		this.dept_no = dept_no;
		this.dept_name = dept_name;
		
			
		
	}


	public Long getDept_no() {
		return dept_no;
	}


	public void setDept_no(Long dept_no) {
		this.dept_no = dept_no;
	}


	public String getDept_name() {
		return dept_name;
	}


	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}


	public List<DoctorBean> getDoctorsList() {
		return doctorsList;
	}


	public void setDoctorsList(List<DoctorBean> doctorsList) {
		this.doctorsList = doctorsList;
	}
	
	@Override
	public String toString()
	{
		return "";
	}
	

}
