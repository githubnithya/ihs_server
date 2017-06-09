package com.psg.ihsserver.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PATIENT_APPOINTMENT")
public class Appointment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="MRDMCG_ONLINE_REG_NO")
	private String online_reg_no;
	@Column(name="MRDTCG_OP_CODE")
	private String op_code;
	@Column(name="MRDTCG_APP_DATE")
	private Date app_date;
	@Column(name="MRDTCG_CONS_DEPT")
	private Integer cons_dept;
	@Column(name="MRDTCG_CONS_DR_NO")
	private Integer cons_dr_no;
	@Column(name="MRDTCG_SER_CODE")
	private String ser_code;
	@Column(name="MRDTCG_RECEIPT_CODE")
	private String receipt_code;
	@Column(name="MRDTCG_CHARGE")
	private Integer charge;
	@Column(name="MRDTCG_RECEIPT_STATUS")
	private String receipt_status;
	@Column(name="MRDTCG_BILL_CODE")
	private String bill_code;
	@Column(name="MRDMCG_VISIT_STATUS")
	private String visit_status;
	@Column(name="MRDTCG_DIV_CODE")
	private String div_code;
	

	public Appointment(){
		
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
	public Date getApp_date() {
		return app_date;
	}
	public void setApp_date(Date app_date) {
		this.app_date = app_date;
	}
	public Integer getCons_dept() {
		return cons_dept;
	}
	public void setCons_dept(Integer cons_dept) {
		this.cons_dept = cons_dept;
	}
	public Integer getCons_dr_no() {
		return cons_dr_no;
	}
	public void setCons_dr_no(Integer cons_dr_no) {
		this.cons_dr_no = cons_dr_no;
	}
	public String getSer_code() {
		return ser_code;
	}
	public void setSer_code(String ser_code) {
		this.ser_code = ser_code;
	}
	public String getReceipt_code() {
		return receipt_code;
	}
	public void setReceipt_code(String receipt_code) {
		this.receipt_code = receipt_code;
	}
	public Integer getCharge() {
		return charge;
	}
	public void setCharge(Integer charge) {
		this.charge = charge;
	}
	public String getReceipt_status() {
		return receipt_status;
	}
	public void setReceipt_status(String receipt_status) {
		this.receipt_status = receipt_status;
	}
	public String getBill_code() {
		return bill_code;
	}
	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}
	public String getVisit_status() {
		return visit_status;
	}
	public void setVisit_status(String visit_status) {
		this.visit_status = visit_status;
	}
	public String getDiv_code() {
		return div_code;
	}
	public void setDiv_code(String div_code) {
		this.div_code = div_code;
	}
	
}
