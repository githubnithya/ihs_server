package com.psg.ihsserver.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTMENT")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3844575247898364100L;
	
	@Id
	@Column(name="MRDTCG_CONS_DEPT", unique = true, nullable = false)
	private Long dept_no;
	@Column(name="MRDTCG_DEPT_NAME")
	private String dept_name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	private List<Doctor> doctorsList;
	
	
	public Department()
	{
		
	}
	
	public List<Doctor> getDoctorsList() {
		return doctorsList;
	}

	public void setDoctorsList(List<Doctor> doctorsList) {
		this.doctorsList = doctorsList;
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
	
	@Override
	public String toString()
	{
		return "";
	}

}
