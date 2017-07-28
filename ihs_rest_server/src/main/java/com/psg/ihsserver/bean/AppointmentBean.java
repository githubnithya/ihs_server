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
		@Override
		public String toString()
		{
			return "";
		}


}
