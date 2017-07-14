package com.psg.ihsserver.service;

import com.psg.ihsserver.dao.UpdatesDao;
import com.psg.ihsserver.daoimpl.UpdatesDaoImpl;

public class UpdatesService {

	UpdatesDao uDao;
	
	public boolean checkLastUpdated(String lastUpdated)
	{
		uDao = new UpdatesDaoImpl();
		return uDao.checkLastUpdated(lastUpdated);
	}
}
