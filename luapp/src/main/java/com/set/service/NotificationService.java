package com.set.service;

import java.util.List;

import com.set.dto.NotificationListDto;
import com.set.model.Notification;
import com.set.model.NotificationDetails;
import com.set.model.NotificationListDetails;
import com.set.model.NotifyRead;
import com.set.model.NotifyReadId;

public interface NotificationService {
	void save(Notification notification);
	void update(Notification notification);

	NotificationListDetails list(int pagenumber, String searchdata);

	NotificationDetails listAllNotification(String classId);
	NotificationDetails listAllNotificationSchool();
	public List<String> getClassIdByTeacherId(String userId);
	List<Object[]> listAllNotificationTeacher(String teacherId);	
	
	int updateNotificationIsActive();

	void saveNotifyRead(NotifyRead notifyRead);

	NotificationDetails listAllNotificationRead();
	public Notification deleteNotification(String userId);
	String getRoleNameByRoleId(String roleId);

	String getClassNameByClassId(String classId);
	boolean isNotificationReadAlreadyInsert(NotifyReadId notifyReadId);
	NotifyRead getNotifyReadById(NotifyReadId notifyReadId);
	Notification getNotificationById(String notificationId);
	NotificationListDetails listClassNotification(int pagenumber, String searchdata,List<String> classId);
}
