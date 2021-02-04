package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NotifyReadId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5058300461194205433L;

	@Column(name = "user_id", columnDefinition = "VARCHAR(16)")
	private String userId;

	@Column(name = "notification_id", columnDefinition = "Varchar(16)")
	private String notificationId;

	public NotifyReadId() {
		// TODO Auto-generated constructor stub
	}

	public NotifyReadId(String userId, String notificationId) {
		this.userId = userId;
		this.notificationId = notificationId;
	}

	public String getStudentId() {
		return userId;
	}

	public void setStudentId(String userId) {
		this.userId = userId;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof NotifyReadId))
			return false;
		NotifyReadId that = (NotifyReadId) o;
		return Objects.equals(getStudentId(), that.getStudentId())
				&& Objects.equals(getNotificationId(), that.getNotificationId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStudentId(), getNotificationId());
	}

}
