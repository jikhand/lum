package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.set.model.Notification;

public class NotificationDetailsDto {
	private List<NotificationDto> NotificationList;
	private int count;
	@javax.persistence.Transient
	private String message;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<NotificationDto> getNotificationDtoList() {
		return NotificationList;
	}

	@JsonProperty("NotificationList")
	public void setDrawingDtoList(List<NotificationDto> notificationList) {
		this.NotificationList = notificationList;
	}
}
