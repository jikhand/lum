package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationInputDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 76954489587683183L;
	private String NotificationType;
	private String NotificationTitle;
	private String NotificationDescription;
	private Date ExpiredAt;
	private String ClassId;
	private String UserId;
	private Date NotifyDate;
	private String NotificationId;
	
	

	
	public String getNotificationId() {
		return NotificationId;
	}
	public void setNotificationId(String notificationId) {
		NotificationId = notificationId;
	}
	public Date getNotifyDate() {
		return NotifyDate;
	}
	@JsonProperty("NotifyDate")
	public void setNotifyDate(Date notifyDate) {
		NotifyDate = notifyDate;
	}

	public Date getExpiredAt() {
		return ExpiredAt;
	}

	@JsonProperty("ExpiredAt")
	public void setExpiredAt(Date expiredAt) {
		ExpiredAt = expiredAt;
	}

	public String getNotificationType() {
		return NotificationType;
	}

	@JsonProperty("NotificationType")
	public void setNotificationType(String notificationType) {
		NotificationType = notificationType;
	}

	public String getNotificationTitle() {
		return NotificationTitle;
	}

	@JsonProperty("NotificationTitle")
	public void setNotificationTitle(String notificationTitle) {
		NotificationTitle = notificationTitle;
	}

	public String getNotificationDescription() {
		return NotificationDescription;
	}

	@JsonProperty("NotificationDescription")
	public void setNotificationDescription(String notificationDescription) {
		NotificationDescription = notificationDescription;
	}

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getUserId() {
		return UserId;
	}

	@JsonProperty("UserId")
	public void setUserId(String userId) {
		UserId = userId;
	}
}
