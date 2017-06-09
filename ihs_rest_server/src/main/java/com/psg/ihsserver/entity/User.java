package com.psg.ihsserver.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="USER_DETAILS")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="PID")
	private Long patient_id;
	
	@Column(name="NAME")
	private String firstName;
	private String lastName;
	@Column(name="AADHAR")
	private String aadharNo;
	@Column(name="PHONE")
	private String phoneNo;
	
	public User()
	{
		
	}
	
	
	
	public Long getPatient_id() {
		return patient_id;
	}



	public void setPatient_id(Long patient_id) {
		this.patient_id = patient_id;
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString()
	{
		return "User [firstName = " + firstName +", lastName = " +lastName +", aadharNo = " + aadharNo +", phoneNo =  "+ phoneNo + "] ";
	}
	
	

}
