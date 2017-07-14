package com.psg.ihsserver.daoimpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.psg.ihsserver.dao.UpdatesDao;
import com.psg.ihsserver.entity.Updates;
import com.psg.ihsserver.util.HibernateUtil;
import com.psg.ihsserver.util.Utils;

public class UpdatesDaoImpl implements UpdatesDao {

	SessionFactory sf;
	
	@Override
	public boolean checkLastUpdated(String lastUpdated) 
	{
		System.out.println("lastUpdated " + lastUpdated);
		Session session = null;
		boolean deptToBeUpdated =false , docToBeUpdated = false;
		sf = HibernateUtil.getSessionFactory();
		
		try
		{
			session = sf.openSession();
			session.beginTransaction();
			//Long clientTime = Utils.generateSQLTimeStamp(lastUpdated);
			java.util.Date clientDate = Utils.generateUtilDate(lastUpdated);
			Criteria criteria = session.createCriteria(Updates.class);
			
			Updates up =  (Updates)criteria.add(Restrictions.eq("table_name","DEPARTMENT").ignoreCase())
											.uniqueResult();
			if(null!= up)
				{
				System.out.println("DEPARTMENT lastModified " + up.getLast_updated());
//				Timestamp tsDept = Utils.generateSQLTimeStamp(Utils.changeTSFormat(up.getLast_updated()));
//				System.out.println("ts " +tsDept);
			

				if(Utils.generateUtilDate(up.getLast_updated()).compareTo(clientDate) > 0)
					{
						deptToBeUpdated = true;
						System.out.println("deptToBeUpdated = true;" + Utils.generateUtilDate(up.getLast_updated()) + clientDate);
					}
				}
		
			Criteria criteriaDoc = session.createCriteria(Updates.class);
			
			Updates upDoc =  (Updates)criteriaDoc.add(Restrictions.eq("table_name","DOCTOR").ignoreCase())
											.uniqueResult();
//			Timestamp tsDoc = Utils.generateSQLTimeStamp(Utils.changeTSFormat(upDoc.getLast_updated()));
//			System.out.println("ts " +tsDoc);
			if(null!= up)
				{
				//System.out.println("DOCTOR lastModified " + tsDoc);
				if(Utils.generateUtilDate(upDoc.getLast_updated()).compareTo(clientDate) > 0)
					{
						docToBeUpdated = true;
						System.out.println("docToBeUpdated = true");
					}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return (deptToBeUpdated || docToBeUpdated)? true : false;
	}
}
