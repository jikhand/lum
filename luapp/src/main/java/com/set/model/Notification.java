package com.set.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "lutbl_notification")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2655824074997659038L;
	@Id
	@Column(name = "notification_id", columnDefinition = "VARCHAR(16)")
	private String notificationId;
	@Column(name = "title", columnDefinition = "VARCHAR(24)")
	private String title;
	@Column(name = "description", columnDefinition = "VARCHAR(200)")
	private String description;
	@Column(name = "is_active", columnDefinition = "int(1) default 1")
	private int isActive;
	@Column(name = "class_id", columnDefinition = "VARCHAR(16)")
	private String classId;
	@Column(name = "userId", columnDefinition = "VARCHAR(8)")
	private String userId;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime; // CreatedDate
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;
	@Column(name = "expairy_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date expairyDate;
	@Column(name = "notify_category", columnDefinition = "VARCHAR(1)")
	private String notifyCategory;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "notify_date")
	private Date notifyDate;
	
	@Column(name = "is_deleted",columnDefinition = "CHAR(1)")
	private String isDeleted;
	
	
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@javax.persistence.Transient
	private String message;

	public String getClassId() {
		return classId;
	}

	public Date getExpairyDate() {
		return expairyDate;
	}

	public void setExpairyDate(Date date) {
		this.expairyDate = date;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotifyCategory() {
		return notifyCategory;
	}

	public void setNotifyCategory(String notifyCategory) {
		this.notifyCategory = notifyCategory;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}

	@javax.persistence.Transient
	private String token;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
