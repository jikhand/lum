package com.set.dao;

import java.util.List;

import com.set.dto.NotificationListDto;
import com.set.model.Notification;
import com.set.model.NotificationDetails;
import com.set.model.NotificationListDetails;
import com.set.model.NotifyRead;
import com.set.model.NotifyReadId;

public interface NotificationDao {
	void save(Notification notification);
	void update(Notification notification);
	public List<String> getClassIdByTeacherId(String userId);
	NotificationDetails listAllNotificationSchool();
	NotificationListDetails list(int pagenumber, String searchdata);
	List<Object[]> listAllNotificationTeacher(String teacherId);
	NotificationDetails listAllNotification(String classId);
	Notification deleteNotification(String notificationId );
	
	//NotificationDetails listAllNotificationTeacher();
	
	int updateNotificationIsActive();

	NotificationDetails listAllNotificationRead();

	void saveNotifyRead(NotifyRead notifyRead);

	String getRoleNameByRoleId(String roleId);

	String getClassNameByClassId(String classId);
	
	boolean isNotificationReadAlreadyInsert(NotifyReadId notifyReadId);

	NotifyRead getNotifyReadById(NotifyReadId notifyReadId);
	Notification getNotificationById(String notificationId);
	NotificationListDetails listClassNotification(int pagenumber,String searchdata,List<String> classId);
}
