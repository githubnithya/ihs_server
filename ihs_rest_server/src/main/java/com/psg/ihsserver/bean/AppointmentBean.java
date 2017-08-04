package com.psg.ihsserver.bean;

import java.io.Serializable;
import java.util.Date;

public class AppointmentBean implements Serializable{

	
	
		private static final long serialVersionUID = 1L;
		private Integer app_id;
		private String online_reg_no;
		private String op_code;
		private Date app_date;
		private Integer cons_dept;
		private Integer cons_dr_no;
		private String ser_code;
		private String receipt_code;
		private Integer charge;
		private String receipt_status;
		private String bill_code;
		private String visit_status;
		private String app_status;
		private String div_code;
		
	    private String tx_statusMsg;
	    private String tx_statusCode;
	    private String tx_amount;
	    private String tx_dateTime;
	    private String tx_merchantTxId;
	    private String tx_merchantCode;
	    private String tx_paymentMethod;
	    private String tx_pg_id;
	    private String tx_refundId;
	    private String tx_checkoutObj;
	    
		public AppointmentBean(){
			
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
