package com.psg.ihsserver.bean;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class PatientBean {
	private String online_reg_no;
	private String op_code;
	private String patient_name;
	private String patient_pwd;
	private String sex;
	private Date dob;
	private String marital_status;
	private String occupation;
	private BigInteger aadhaar_no;
	private String address;
	private String area_name;
	public PatientBean(String online_reg_no, String op_code, String patient_name, String patient_pwd, String sex,
			Date dob, String marital_status, String occupation, BigInteger aadhaar_no, String address, String area_name,
			String city_name, String state, String pincode, BigInteger phone, BigInteger mobile_no, String mail_id,
			String dependent_type, String dependent_name, String dependent_occupation, BigInteger dependent_aadhaar_no,
			String div_code) {
		super();
		this.online_reg_no = online_reg_no;
		this.op_code = op_code;
		this.patient_name = patient_name;
		this.patient_pwd = patient_pwd;
		this.sex = sex;
		this.dob = dob;
		this.marital_status = marital_status;
		this.occupation = occupation;
		this.aadhaar_no = aadhaar_no;
		this.address = address;
		this.area_name = area_name;
		this.city_name = city_name;
		this.state = state;
		this.pincode = pincode;
		this.phone = phone;
		this.mobile_no = mobile_no;
		this.mail_id = mail_id;
		this.dependent_type = dependent_type;
		this.dependent_name = dependent_name;
		this.dependent_occupation = dependent_occupation;
		this.dependent_aadhaar_no = dependent_aadhaar_no;
		this.div_code = div_code;
	}
	private String city_name;
	private String state;
	private String pincode;
	private BigInteger phone;
	private BigInteger mobile_no;
	private String mail_id;
	private String dependent_type;
	private String dependent_name;
	private String dependent_occupation;
	private BigInteger dependent_aadhaar_no;
	private String div_code;
	
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
	
	
	
}
