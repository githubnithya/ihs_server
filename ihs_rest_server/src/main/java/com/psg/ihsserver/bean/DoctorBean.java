package com.psg.ihsserver.bean;

import java.io.Serializable;


public class DoctorBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long  doc_no;
	
	private String doc_name;
	
	private DepartmentBean department;
	
	public DoctorBean(Long doc_no, String doc_name, DepartmentBean dept){
		this.doc_no = doc_no;
		this.doc_name = doc_name;
		this.department = dept;
		
	}

	public Long getDoc_no() {
		return doc_no;
	}

	public void setDoc_no(Long doc_no) {
		this.doc_no = doc_no;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public DepartmentBean getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}
	
	@Override
	public String toString()
	{
		return "";
	}
	
	
}
