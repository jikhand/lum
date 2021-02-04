package com.set.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class NotificationListDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4704134689402438300L;
	private List<Map<String, String>> NotificationList;
	private int count;
	public List<Map<String, String>> getNotificationList() {
		return NotificationList;
	}
	public void setNotificationList(List<Map<String, String>> notificationList) {
		NotificationList = notificationList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}