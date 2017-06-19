package com.psg.ihsserver.dao;

import java.sql.Date;

public interface TableUpdatesDao {
	
	public Date getLastUpdatedForTable(String tableName);

}
