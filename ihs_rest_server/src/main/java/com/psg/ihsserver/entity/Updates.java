package com.psg.ihsserver.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TABLE_UPDATES")
public class Updates implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -18315103768247608L;
	
	@Id
	@Column(name="TABLE_NAME")
	private String table_name;
	@Column(name="LAST_UPDATED")
	private Timestamp last_updated;
	
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public Timestamp getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Timestamp last_updated) {
		this.last_updated = last_updated;
	}
	
	@Override
	public String toString()
	{
		return "";
	}
	
}
