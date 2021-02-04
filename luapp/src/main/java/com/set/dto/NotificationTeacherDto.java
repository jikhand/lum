package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationTeacherDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 76954489587683183L;
	private String Id;
	private String Status;
	private String NotificationType;
	private String NotificationTitle;
	private String NotificationDescription;
	private String CreatedByUserId;
	private Date CreatedDate;
	private String ClassId;	
	private String IsRead;
	private Date ExpiredAt;
	
	
	public Date getExpiredAt() {
		return ExpiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		ExpiredAt = expiredAt;
	}

	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public String getStatus() {
		return Status;
	}

	@JsonProperty("Status")
	public void setStatus(String status) {
		Status = status;
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

	public String getCreatedByUserId() {
		return CreatedByUserId;
	}

	@JsonProperty("CreatedByUserId")
	public void setCreatedByUserId(String createdByUserId) {
		CreatedByUserId = createdByUserId;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}
	@JsonProperty("CreatedDate")
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getIsRead() {
		return IsRead;
	}
	@JsonProperty("IsRead")
	public void setIsRead(String isRead) {
		IsRead = isRead;
	}
}
