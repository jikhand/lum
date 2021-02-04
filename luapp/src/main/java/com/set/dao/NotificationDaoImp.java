package com.set.dao;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.set.dto.NotificationDetailsDto;
import com.set.dto.NotificationDto;
import com.set.dto.NotificationListDto;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.Notification;
import com.set.model.NotificationDetails;
import com.set.model.NotificationListDetails;
import com.set.model.NotifyRead;
import com.set.model.NotifyReadId;
import com.set.model.Role;
import com.set.service.ClassSectionMasterService;
import com.set.utils.CommonUtils;

@Repository
public class NotificationDaoImp implements NotificationDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ClassSectionMasterService classSectionMasterService;
	private Logger logger = Logger.getLogger("NotificationDaoImp.class");

	@Override
	public void save(Notification notification) {
		sessionFactory.getCurrentSession().save(notification);
	}

//	@Override
//	public NotificationDetails list(int pagenumber, String searchdata) {
//		int maxPageData = 10;
//		int start = pagenumber * maxPageData - maxPageData;
//		int end = 10;
//		String searchString = "";
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Notification.class);
//		if (!searchdata.equalsIgnoreCase("null")) {
//			String matchString = "%" + searchdata + "%";
//			Disjunction or = Restrictions.disjunction();
//			or.add(Restrictions.like("title", matchString, MatchMode.ANYWHERE))
//					.add(Restrictions.like("description", matchString, MatchMode.ANYWHERE));
//					//.add(Restrictions.like("classId", matchString, MatchMode.ANYWHERE));
//
//			criteria.add(or);
//		}
//		System.out.println("title +title");
//		System.out.println("description +description");
//		NotificationDetails notificationDetails = new NotificationDetails();
//		logger.info("total number of record=" + criteria.list().size());
//		notificationDetails.setCount(criteria.list().size());
//		notificationDetails.setNotifications((List<Notification>) criteria.list());
//		criteria.setFirstResult(pagenumber * maxPageData - maxPageData);
//		criteria.setMaxResults(end);
//		return notificationDetails;
//	}
	@Override
	public NotificationListDetails list(int pagenumber, String searchdata) {
		NotificationListDetails notificationListDetails =  new NotificationListDetails();
		int maxPageData = 10;
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
	
		if (!searchdata.equalsIgnoreCase("null")) {
			searchString="  and description LIKE '%"+searchdata+"%' or notify_category LIKE '%"+searchdata +"%' "
					+ "or title LIKE '%"+searchdata+"%' or class_name LIKE '%"+searchdata+"%'";
		}
		String strQuery="SELECT any_value(ln.notification_id) notification_id,any_value(ln.class_id) class_id,"
				+ " any_value(ln.description) description,any_value(ln.expairy_date) expairy_date,"
				+ "any_value(ln.inserted_by) inserted_by,any_value(ln.inserted_time) inserted_time,"
				+ "any_value(ln.is_active) is_active,any_value(ln.notify_category) notify_category,"
				+ "any_value(ln.notify_date) notify_date,any_value(ln.title) title,any_value(ln.updated_by) "
				+ "updated_by,any_value(ln.updated_time) updated_time,any_value(ln.userId) userId,"
				+ "any_value(csm.class_name) class_name FROM lutbl_notification as ln LEFT OUTER JOIN "
				+ "lutbl_class_sec_master as csm on (ln.class_id=csm.class_id and notify_category=\"C\") where is_deleted='0'"+searchString+" GROUP BY notification_id";
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery(strQuery);
		System.out.println("query=" + query);
		List<Object[]> totalrows = query.list();
		int totalNumbers = totalrows.size();
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		for (Object[] row : rows) {
			Map<String, String> hm = new HashMap<String, String>();
			if (null != row[0]) {
				hm.put("NotificationId", row[0].toString());
			} else {
				hm.put("NotificationId", "");
			}

			if (null != row[1]) {
				hm.put("ClassId", row[1].toString());
			} else {
				hm.put("ClassId", "");
			}
			if (null != row[2]) {
				hm.put("Description", row[2].toString());
			} else {
				hm.put("Description", row[2].toString());
			}
			if (null != row[3]) {
				hm.put("ExpairyDate", row[3].toString());
			} else {
				hm.put("ExpairyDate", "");
			}
			if (null != row[4]) {
				hm.put("inserted_by", row[4].toString());
			} else {
				hm.put("inserted_by", "");
			}
			if (null != row[5]) {
				hm.put("inserted_time", row[5].toString());
			} else {
				hm.put("inserted_time", "");
			}
			if (null != row[6]) {
				hm.put("is_active", row[6].toString());
			} else {
				hm.put("is_active", "");
			}
			if (null != row[7]) {
				hm.put("notify_category", row[7].toString());
			} else {
				hm.put("notify_category", "");
			}
			if (null != row[8]) {
				hm.put("notify_date",row[8].toString());
			} else {
				hm.put("notify_date", "");
			}
			if (null != row[9]) {
				hm.put("title", row[9].toString());
			} else {
				hm.put("title", "");
			}
			if (null != row[10]) {
				hm.put("updated_by", row[10].toString());
			} else {
				hm.put("updated_by", "");
			}
			if (null != row[11]) {
				hm.put("updated_time", row[11].toString());
			} else {
				hm.put("updated_time", "");
			}
			if (null != row[12]) {
				hm.put("userId", row[12].toString());
			} else {
				hm.put("userId", "");
			}
			if (null != row[13]) {
				hm.put("class_name", row[13].toString());
			} else {
				hm.put("class_name", "");
			}
			arrayList.add(hm);
		}
		System.out.println("totalNumbers="+totalNumbers);
		System.out.println("array list size="+arrayList.size());
		notificationListDetails.setNotificationList(arrayList);
		notificationListDetails.setCount(totalNumbers);
		notificationListDetails.setMessage("List of Notification");
		return notificationListDetails;
		}
	
	@Override
	public NotificationListDetails listClassNotification(int pagenumber, String searchdata,List<String> classId) {
		NotificationListDetails notificationListDetails =  new NotificationListDetails();
		int maxPageData = 10;
		String ar = classId.get(0);
		System.out.println("ar.toString="+ar);
		String teacherId = Arrays.toString(classId.toArray()).replace("[","'").replace("]","'").replace(",", "',").replace(" ", "'")
			    .trim(); 
		int start = pagenumber * maxPageData - maxPageData;
		int end = 10;
		String searchString = "";
	    //System.out.println("testString========="+testString);
		if (!searchdata.equalsIgnoreCase("null")) {
			searchString="  and description LIKE '%"+searchdata+"%' or notify_category LIKE '%"+searchdata +"%' "
					+ "or title LIKE '%"+searchdata+"%' or class_name LIKE '%"+searchdata+"%'";
		}
		String strQuery="SELECT any_value(ln.notification_id) notification_id,any_value(ln.class_id) class_id,"
				+ " any_value(ln.description) description,any_value(ln.expairy_date) expairy_date,"
				+ "any_value(ln.inserted_by) inserted_by,any_value(ln.inserted_time) inserted_time,"
				+ "any_value(ln.is_active) is_active,any_value(ln.notify_category) notify_category,"
				+ "any_value(ln.notify_date) notify_date,any_value(ln.title) title,any_value(ln.updated_by) "
				+ "updated_by,any_value(ln.updated_time) updated_time,any_value(ln.userId) userId,"
				+ "any_value(csm.class_name) class_name FROM lutbl_notification as ln LEFT OUTER JOIN "
				+ "lutbl_class_sec_master as csm on (ln.class_id=csm.class_id and notify_category=\"C\") where is_deleted='0' and  (notify_category='S' or (notify_category='C' and ln.class_id in("+teacherId+"))) "+searchString+" GROUP BY notification_id";
		SQLQuery query = sessionFactory.getCurrentSession()
				.createSQLQuery(strQuery);
		System.out.println("query=" + query);
		List<Object[]> totalrows = query.list();
		int totalNumbers = totalrows.size();
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<Object[]> rows = query.list();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		for (Object[] row : rows) {
			Map<String, String> hm = new HashMap<String, String>();
			if (null != row[0]) {
				hm.put("NotificationId", row[0].toString());
			} else {
				hm.put("NotificationId", "");
			}

			if (null != row[1]) {
				hm.put("ClassId", row[1].toString());
			} else {
				hm.put("ClassId", "");
			}
			if (null != row[2]) {
				hm.put("Description", row[2].toString());
			} else {
				hm.put("Description", row[2].toString());
			}
			if (null != row[3]) {
				hm.put("ExpairyDate", row[3].toString());
			} else {
				hm.put("ExpairyDate", "");
			}
			if (null != row[4]) {
				hm.put("inserted_by", row[4].toString());
			} else {
				hm.put("inserted_by", "");
			}
			if (null != row[5]) {
				hm.put("inserted_time", row[5].toString());
			} else {
				hm.put("inserted_time", "");
			}
			if (null != row[6]) {
				hm.put("is_active", row[6].toString());
			} else {
				hm.put("is_active", "");
			}
			if (null != row[7]) {
				hm.put("notify_category", row[7].toString());
			} else {
				hm.put("notify_category", "");
			}
			if (null != row[8]) {
				hm.put("notify_date",row[8].toString());
			} else {
				hm.put("notify_date", "");
			}
			if (null != row[9]) {
				hm.put("title", row[9].toString());
			} else {
				hm.put("title", "");
			}
			if (null != row[10]) {
				hm.put("updated_by", row[10].toString());
			} else {
				hm.put("updated_by", "");
			}
			if (null != row[11]) {
				hm.put("updated_time", row[11].toString());
			} else {
				hm.put("updated_time", "");
			}
			if (null != row[12]) {
				hm.put("userId", row[12].toString());
			} else {
				hm.put("userId", "");
			}
			if (null != row[13]) {
				hm.put("class_name", row[13].toString());
			} else {
				hm.put("class_name", "");
			}
			arrayList.add(hm);
		}
		System.out.println("totalNumbers="+totalNumbers);
		System.out.println("array list size="+arrayList.size());
		notificationListDetails.setNotificationList(arrayList);
		notificationListDetails.setCount(totalNumbers);
		notificationListDetails.setMessage("List of Notification");
		return notificationListDetails;
		}

	// will display only active data irrespective of read/unread
	@Override
	public NotificationDetails listAllNotification(String classId) {

		NotificationDetails notificationDetails = new NotificationDetails();
//		Query query = sessionFactory.getCurrentSession().createQuery(
//				"Select n.notificationId,n.title, n.description,n.notifyCategory,nr.isRead,n.expairyDate,nr.NotifyReadId.userId, n.classId"
//						+ " from Notification n, NotifyRead nr where n.notificationId=nr.NotifyReadId.notificationId and n.isActive=1");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Notification.class);
		criteria.add(Restrictions.eq("isActive", 1));
		List<Notification> notificationList = criteria.list();
		notificationDetails.setNotifications(notificationList);
		notificationDetails.setCount(notificationList.size());
		return notificationDetails;
	}
	
	@Override
	public NotificationDetails listAllNotificationSchool() {

		NotificationDetails notificationDetails = new NotificationDetails();
//		Query query = sessionFactory.getCurrentSession().createQuery(
//				"Select n.notificationId,n.title, n.description,n.notifyCategory,nr.isRead,n.expairyDate,nr.NotifyReadId.userId, n.classId"
//						+ " from Notification n, NotifyRead nr where n.notificationId=nr.NotifyReadId.notificationId and n.isActive=1");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Notification.class);
		//criteria.add(Restrictions.eq("classId", classId));
		List<Notification> notificationList = criteria.list();
		notificationDetails.setNotifications(notificationList);
		notificationDetails.setCount(notificationList.size());
		return notificationDetails;
	}
	
	
//	@Override
//	public NotificationDetails listAllNotificationTeacher() {
//
//		NotificationDetails notificationDetails = new NotificationDetails();
////		Query query = sessionFactory.getCurrentSession().createQuery(
////				"Select n.notificationId,n.title, n.description,n.notifyCategory,nr.isRead,n.expairyDate,nr.NotifyReadId.userId, n.classId"
////						+ " from Notification n, NotifyRead nr where n.notificationId=nr.NotifyReadId.notificationId and n.isActive=1");
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Notification.class);
//		//criteria.add(Restrictions.gt("expairyDate", CommonUtils.getCurrentDateTime()));
//		List<Notification> notificationList = criteria.list();
//		notificationDetails.setNotifications(notificationList);
//		notificationDetails.setCount(notificationList.size());
//		return notificationDetails;
//	}
//	@Override
//	public NotificationDetails listAllNotificationTeacher(List<String> classId) {
//
//		NotificationDetails notificationDetails = new NotificationDetails();
//		Object [] ar = classId.toArray();
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Notification.class);
//		try {
//			Disjunction or = Restrictions.disjunction();
//
//			Calendar c=new GregorianCalendar();
//			c.add(Calendar.DATE, 15);
//			Date d=c.getTime();
//			
//
////		    SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
////		    fmt.setCalendar(c);
////		    String dateFormatted = fmt.format(c.getTime());
//			
//			System.out.println("date"+d);
//			or.add(Restrictions.eq("notifyCategory", "S")).add(Restrictions.in("classId",ar));
//			or.add(Restrictions.ge("expairyDate", d)).add(Restrictions.in("classId",ar));
//		    criteria.add(or);
//			List<Notification> notificationList = criteria.list();
//			notificationDetails.setNotifications(notificationList);
//			notificationDetails.setCount(notificationList.size());
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return notificationDetails;
//	}
	

	@Override
	public List<Object[]> listAllNotificationTeacher(String teacherId) {

		NotificationDetails notificationDetails = new NotificationDetails();

		String StrQuery = "SELECT n.notification_id, n.class_id, n.description, n.expairy_date, n.inserted_by,"
				+ " n.inserted_time, n.is_active, n.notify_category, n.notify_date, n.title, n.updated_by,"
				+ " n.updated_time, n.userId FROM lutbl_notification n WHERE "
				+ " NOW() <= DATE(DATE_ADD(n.expairy_date, INTERVAL +15 DAY)) and (n.notify_category='S' or n.class_id IN "
				+ "(select class_id FROM lutbl_class_subj cs WHERE cs.teacher_id='"+teacherId+"'))";
		SQLQuery daySql = sessionFactory.getCurrentSession().createSQLQuery(StrQuery);
		return  (List<Object[]>) daySql.list();
	}
	
	@Override
	public int updateNotificationIsActive() {
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("UPDATE lutbl_notification SET is_active = '0'"
				+ " WHERE expairy_date<now() and is_active <> 0");
		
		return query.executeUpdate();		
	}

	@Override
	public void saveNotifyRead(NotifyRead notifyRead) {
		sessionFactory.getCurrentSession().save(notifyRead);
	}

	@Override
	public NotificationDetails listAllNotificationRead() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NotifyRead.class);
		NotificationDetails notificationDetails = new NotificationDetails();
		logger.info("total number of record=" + criteria.list().size());
		notificationDetails.setCount(criteria.list().size());

		notificationDetails.setNotifications((List<Notification>) criteria.list());

		return notificationDetails;
	}

	@Override
	public String getRoleNameByRoleId(String roleId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Role.class);
		criteria.add(Restrictions.eq("roleId", roleId));
		Role role = (Role) criteria.uniqueResult();
		if (role != null)
			return role.getRoleDescription();
		return null;
	}

	@Override
	public String getClassNameByClassId(String classId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassSectionMaster.class);
		criteria.add(Restrictions.eq("classSectionMasterId.classId", classId));
		ClassSectionMaster classIdCheck = (ClassSectionMaster) criteria.uniqueResult();

		if (classIdCheck != null)
			return classIdCheck.getClassName();

		return null;
	}

	@Override
	public NotifyRead getNotifyReadById(NotifyReadId notifyReadId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NotifyRead.class);
		criteria.add(Restrictions.eq("NotifyReadId", notifyReadId));
		NotifyRead notifyRead = (NotifyRead) criteria.uniqueResult();
		return notifyRead;
	}

	@Override
	public boolean isNotificationReadAlreadyInsert(NotifyReadId notifyReadId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NotifyRead.class);
		criteria.add(Restrictions.eq("NotifyReadId", notifyReadId));
		List<NotifyRead> notifyRead = (List<NotifyRead>) criteria.list();
		if(notifyRead.size()>0) {
			return false;	
		}else {
		return true;
		}
	}

	@Override
	public Notification getNotificationById(String notificationId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Notification.class);
		criteria.add(Restrictions.eq("notificationId", notificationId));
		Notification notification = (Notification) criteria.uniqueResult();
		return notification;
	}

	@Override
	public void update(Notification notification) {
		sessionFactory.getCurrentSession().update(notification);		
	}
	@Override
	public List<String> getClassIdByTeacherId(String userId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("Select distinct class_id from lutbl_class_subj cs where cs.teacher_id = '"+userId+"'");
		List<String> listOfTextBooks = query.list();
		return listOfTextBooks;	
	}

	@Override
	public Notification deleteNotification(String userId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Notification.class);
		criteria.add(Restrictions.eq("notificationId", userId));
		Notification notification = (Notification) criteria.uniqueResult();
		notification.setIsDeleted("1");
		sessionFactory.getCurrentSession().update(notification);
		return notification;	
	}

	
}