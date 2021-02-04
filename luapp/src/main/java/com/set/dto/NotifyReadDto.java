package com.set.dto;

import java.io.Serializable;
import java.sql.Date;

public class NotifyReadDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2169382539954387952L;
	private String userId;
	private String notificationId;
	private String isRead;
	private String readOn;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getReadOn() {
		return readOn;
	}

	public void setReadOn(String readOn) {
		this.readOn = readOn;
	}
}
