package com.psg.ihsserver.entity;

import java.io.Serializable;
import java.math.BigInteger;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="PATIENT_REGISTRATION")
public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="MRDMCG_ONLINE_REG_NO", unique = true, nullable = false)
	private String online_reg_no;
	@Column(name="MRDMCG_OP_CODE")
	private String op_code;
	@Column(name="MRDMCG_PATIENT_NAME")
	private String patient_name;
	@Column(name="MRDMCG_PWD")
	private String patient_pwd;
	@Column(name="MRDMCG_SEX")
	private String sex;
	@Column(name="MRDMCG_DOB")
	
	private Date dob;
	@Column(name="MRDMCG_MARITAL_STATUS")
	private String marital_status;
	@Column(name="MRDMCG_OCCUPATION")
	private String occupation;
	@Column(name="MRDMCG_AADHAAR_NO")
	private BigInteger aadhaar_no;
	@Column(name="MRDMCG_ADDRESS")
	private String address;
	@Column(name="MRDMCG_AREA_NAME")
	private String area_name;
	@Column(name="MRDMCG_CITY_NAME")
	private String city_name;
	@Column(name="MRDMCG_STATE")
	private String state;
	@Column(name="MRDMCG_PINCODE")
	private String pincode;
	@Column(name="MRDMCG_PHONE")
	private BigInteger phone;
	@Column(name="MRDMCG_MOBILE_NO")
	
	private BigInteger mobile_no;
	@Column(name="MRDMCG_MAIL_ID")
	private String mail_id;
	@Column(name="MRDMCG_DEPENDENT_TYPE")
	private String dependent_type;
	@Column(name="MRDMCG_DEPENDENT_NAME")
	private String dependent_name;
	@Column(name="MRDMCG_DEPENDENT_OCCUPATION")
	private String dependent_occupation;
	@Column(name="MRDMCG_DEPENDENT_AADHAAR_NO")
	private BigInteger dependent_aadhaar_no;
	@Column(name="MRDMCG_DIV_CODE")
	private String div_code;
	
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getOnline_reg_no() {
		return online_reg_no;
	}
	public void setOnline_reg_no(String online_reg_no) {
		this.online_reg_no = online_reg_no;
	}
	public String getOp_code() {
		return op_code;
	}
	public void setOp_code(String op_code) {
		this.op_code = op_code;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	
	public String getPatient_pwd() {
		return patient_pwd;
	}


	public void setPatient_pwd(String patient_pwd) {
		this.patient_pwd = patient_pwd;
	}


	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public BigInteger getAadhaar_no() {
		return aadhaar_no;
	}
	public void setAadhaar_no(BigInteger aadhaar_no) {
		this.aadhaar_no = aadhaar_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public BigInteger getPhone() {
		return phone;
	}
	public void setPhone(BigInteger phone) {
		this.phone = phone;
	}
	public BigInteger getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(BigInteger mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getMail_id() {
		return mail_id;
	}
	public void setMail_id(String mail_id) {
		this.mail_id = mail_id;
	}
	public String getDependent_type() {
		return dependent_type;
	}
	public void setDependent_type(String dependent_type) {
		this.dependent_type = dependent_type;
	}
	public String getDependent_name() {
		return dependent_name;
	}
	public void setDependent_name(String dependent_name) {
		this.dependent_name = dependent_name;
	}
	public String getDependent_occupation() {
		return dependent_occupation;
	}
	public void setDependent_occupation(String dependent_occupation) {
		this.dependent_occupation = dependent_occupation;
	}
	public BigInteger getDependent_aadhaar_no() {
		return dependent_aadhaar_no;
	}
	public void setDependent_aadhaar_no(BigInteger dependent_aadhaar_no) {
		this.dependent_aadhaar_no = dependent_aadhaar_no;
	}
	public String getDiv_code() {
		return div_code;
	}
	public void setDiv_code(String div_code) {
		this.div_code = div_code;
	}
	
	@Override
	public String toString()
	{
		return "Patient [online_reg_no = " + online_reg_no +", patient_name = " +patient_name +"] ";
	}
	
	
}
