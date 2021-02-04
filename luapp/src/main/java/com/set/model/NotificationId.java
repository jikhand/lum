package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationId{
	/**
	 * 
	 */
	private String NotificationId;

	public String getTeacherId() {
		return NotificationId;
	}

	@JsonProperty("NotificationId")
	public void setTeacherId(String notificationId) {
		NotificationId = notificationId;
	}
	
}
