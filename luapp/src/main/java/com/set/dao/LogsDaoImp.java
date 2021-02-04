package com.set.dao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.set.model.TblLogs;
import com.set.model.User;
import com.set.utils.UtilDate;
@Repository
public class LogsDaoImp implements LogsDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger("LogsDaoImp.class");
	@SuppressWarnings("unchecked")

	@Override
	public List<Map<String , String>> list(int pagenumber,String searchdata) {
		String searchString="";
		LocalDateTime datetime=null;
		if (!searchdata.equalsIgnoreCase("null")) {
			logger.info("valid date");
//			if(UtilDate.isValidDate(searchdata)) {
//				System.out.println("new datetime nre "+datetime);
//				datetime = LocalDateTime.parse(searchdata,DateTimeFormatter.ofPattern("yyyy-MM-dd"));	//DATE_FORMAT('2009-10-04 22:23:00', '%Y/%m/%d')
//			}
			searchString=" where ActionType LIKE '%"+searchdata+"%' or Email LIKE '%"+searchdata +"%' or ActionElement LIKE '%"+searchdata+"%' or CAST(l.CreatedAt AS char) LIKE '%"+searchdata+"%'";
		}
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " ActionType,Email,ActionElement,l.CreatedAt FROM tbllogs as l "
			    + "join tbl_user as u on u.userId=l.ActionPerformedById "+searchString
			    + " GROUP BY l.Id DESC ");
		List<Object[]> totalrows = query.list();
		int totalNumbers=totalrows.size();
		int maxPageData=10;
		int start = pagenumber * maxPageData - maxPageData;
		int end =10;
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String , String>> arrayList  = new ArrayList<Map<String,String>>();
//		Map<String,String> hm1 = new HashMap<String, String>();
//		hm1.put("RecordTotal",String.valueOf(totalNumbers));
//		arrayList.add(hm1);
		for(Object[] row : rows){
			Map<String,String> hm = new HashMap<String, String>();
				hm.put("ActionType", row[0].toString());
				hm.put("Email", row[1].toString());
				hm.put("ActionElement", row[2].toString());
				hm.put("CreatedAt", row[3].toString());
				hm.put("RecordTotal",String.valueOf(totalNumbers));
				arrayList.add(hm);
			}
			return arrayList;
	}
	
	
	
	@Override
	public void save(TblLogs tblLogs) {
		sessionFactory.getCurrentSession().save(tblLogs);
	}
	@Override
	public int totalLogs() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT"
				+ " ActionType,Email,ActionElement,l.CreatedAt FROM tbllogs as l "
			    + "join tbl_user as u on u.userId=l.ActionPerformedById "
			    + "GROUP BY l.Id DESC ");
		List<Object[]> totalrows = query.list();
			return totalrows.size();
	}
}
