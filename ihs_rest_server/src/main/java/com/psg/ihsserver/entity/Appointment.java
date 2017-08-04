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
	@Column(name="MRDTCG_APP_ID")
	private Integer app_id;
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
	@Column(name="MRDTCG_APP_STATUS")
	private String app_status;
	@Column(name="MRDTCG_DIV_CODE")
	private String div_code;
	
	
	
	 @Column(name="MRDTCG_STATUSMSG")
	    private String tx_statusMsg;
	    @Column(name="MRDTCG_STATUSCODE")
	    private String tx_statusCode;
	    @Column(name="MRDTCG_AMOUNT")
	    private String tx_amount;
	    @Column(name="MRDTCG_DATETIME")
	    private String tx_dateTime;
	    @Column(name="MRDTCG_MERCHANTID")
	    private String tx_merchantTxId;
	    @Column(name="MRDTCG_MERCHANTCODE")
	    private String tx_merchantCode;
	    @Column(name="MRDTCG_PAYMENTMETHOD")
	    private String tx_paymentMethod;
	    @Column(name="MRDTCG_PG_ID")
	    private String tx_pg_id;
	    @Column(name="MRDTCG_REFUNDID")
	    private String tx_refundId;
	    @Column(name="MRDTCG_CHECKOUTOBJ")
	    private String tx_checkoutObj;
	    

	public Appointment(){
		
	}
	
	public Integer getApp_id() {
		return app_id;
	}

	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
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
	
	public String getApp_status() {
		return app_status;
	}

	public void setApp_status(String app_status) {
		this.app_status = app_status;
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
	
	public String getTx_statusMsg() {
		return tx_statusMsg;
	}

	public void setTx_statusMsg(String tx_statusMsg) {
		this.tx_statusMsg = tx_statusMsg;
	}

	public String getTx_statusCode() {
		return tx_statusCode;
	}

	public void setTx_statusCode(String tx_statusCode) {
		this.tx_statusCode = tx_statusCode;
	}

	public String getTx_amount() {
		return tx_amount;
	}

	public void setTx_amount(String tx_amount) {
		this.tx_amount = tx_amount;
	}

	public String getTx_dateTime() {
		return tx_dateTime;
	}

	public void setTx_dateTime(String tx_dateTime) {
		this.tx_dateTime = tx_dateTime;
	}

	public String getTx_merchantTxId() {
		return tx_merchantTxId;
	}

	public void setTx_merchantTxId(String tx_merchantTxId) {
		this.tx_merchantTxId = tx_merchantTxId;
	}

	public String getTx_merchantCode() {
		return tx_merchantCode;
	}

	public void setTx_merchantCode(String tx_merchantCode) {
		this.tx_merchantCode = tx_merchantCode;
	}

	public String getTx_paymentMethod() {
		return tx_paymentMethod;
	}

	public void setTx_paymentMethod(String tx_paymentMethod) {
		this.tx_paymentMethod = tx_paymentMethod;
	}

	public String getTx_pg_id() {
		return tx_pg_id;
	}

	public void setTx_pg_id(String tx_pg_id) {
		this.tx_pg_id = tx_pg_id;
	}

	public String getTx_refundId() {
		return tx_refundId;
	}

	public void setTx_refundId(String tx_refundId) {
		this.tx_refundId = tx_refundId;
	}

	public String getTx_checkoutObj() {
		return tx_checkoutObj;
	}

	public void setTx_checkoutObj(String tx_checkoutObj) {
		this.tx_checkoutObj = tx_checkoutObj;
	}

	@Override
	public String toString()
	{
		return "";
	}
}
