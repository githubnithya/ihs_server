package com.psg.ihsserver.bean;

import java.io.Serializable;

public class DoctorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long doc_no;

	private String doc_name;

	private Long doc_fee;
	private String doc_availability;

	private DepartmentBean department;

	public DoctorBean(Long doc_no, String doc_name, Long doc_fee, String doc_availability, DepartmentBean dept) {
		this.doc_no = doc_no;
		this.doc_name = doc_name;
		this.doc_fee = doc_fee;
		this.doc_availability = doc_availability;
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

	public Long getDoc_fee() {
		return doc_fee;
	}

	public void setDoc_fee(Long doc_fee) {
		this.doc_fee = doc_fee;
	}

	public String getDoc_availability() {
		return doc_availability;
	}

	public void setDoc_availability(String doc_availability) {
		this.doc_availability = doc_availability;
	}

	public DepartmentBean getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "";
	}

}
