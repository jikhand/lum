package com.set.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.set.dao.NotificationDao;
import com.set.dto.NotificationListDto;
import com.set.model.Notification;
import com.set.model.NotificationDetails;
import com.set.model.NotificationListDetails;
import com.set.model.NotifyRead;
import com.set.model.NotifyReadId;
@Service
@Transactional
public class NotificationServiceImp implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;
	
	/**  
	 * @description save
	 * @param @notification
	 * @return void
	 * etc
	 */
	@Override
	public void save(Notification notification) {
		notificationDao.save(notification);
	}
	/**  
	 * @description notification details
	 * @param @pagenumber, @searchdata
	 * @return Notificationdetails
	 * etc
	 */
	
	@Override
	public NotificationListDetails list(int pagenumber, String searchdata) {
		// TODO Auto-generated method stub
		return notificationDao.list(pagenumber, searchdata);
	}

	@Override
	public NotificationDetails listAllNotification(String classId) {
		return notificationDao.listAllNotification(classId);
	}
	
	@Override
	public NotificationDetails listAllNotificationSchool() {
		return notificationDao.listAllNotificationSchool();
	}
	
	@Override
	public List<Object[]> listAllNotificationTeacher(String teacherId) {
		return notificationDao.listAllNotificationTeacher(teacherId);
	}

	
	@Override
	public void saveNotifyRead(NotifyRead notifyRead) {
		// TODO Auto-generated method stub
		notificationDao.saveNotifyRead(notifyRead);
	}
	
	@Override
	public NotificationDetails listAllNotificationRead() {
		// TODO Auto-generated method stub
		return notificationDao.listAllNotificationRead();
	}
	
	@Override
	public int updateNotificationIsActive() {
		// TODO Auto-generated method stub
		return notificationDao.updateNotificationIsActive();
	}
	
	@Override
	public String getRoleNameByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return notificationDao.getRoleNameByRoleId(roleId);
	}
	
	@Override
	public String getClassNameByClassId(String classId) {
		return notificationDao.getClassNameByClassId(classId);
	}
	@Override
	public NotifyRead getNotifyReadById(NotifyReadId notifyReadId) {
		return notificationDao.getNotifyReadById(notifyReadId);
	}
	@Override
	public boolean isNotificationReadAlreadyInsert(NotifyReadId notifyReadId) {
		return notificationDao.isNotificationReadAlreadyInsert(notifyReadId);
	}
	@Override
	public Notification getNotificationById(String notificationId) {
		return notificationDao.getNotificationById(notificationId);
	}
	@Override
	public void update(Notification notification) {
		notificationDao.update(notification);
		
	}
	@Override
	public List<String> getClassIdByTeacherId(String userId){
		return notificationDao.getClassIdByTeacherId(userId);
	}
	@Override
	public Notification deleteNotification(String userId) {
		// TODO Auto-generated method stub
		return notificationDao.deleteNotification(userId);
	}
	
	@Override
	public NotificationListDetails listClassNotification(int pagenumber, String searchdata,List<String> classId) {
	// TODO Auto-generated method stub
	return notificationDao.listClassNotification(pagenumber, searchdata,classId);
	}
}
