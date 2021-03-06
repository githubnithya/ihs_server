package com.psg.ihsserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="DOCTOR")
public class Doctor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -18315103768247608L;

	@Id
	@Column(name="MRDMCG_CONS_DR_NO",unique = true, nullable = false)
	private Long  doc_no;
	@Column(name="MRDMCG_DOC_NAME")
	private String doc_name;
	
	@Column(name="MRDMCG_DOC_FEE")
	private Long doc_fee;
	
	@Column(name="MRDMCG_DOC_A")
	private String doc_availability;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MRDTCG_CONS_DEPT", nullable = false)
	private Department department;
	
	
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	@Override
	public String toString()
	{
		return "";
	}
	
}
