package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SportsActivityTeacherDto implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7533879781239354620L;
	
	private int activityCode;
	private String activityDescription;
	private String insertedBy;
	private Date insertedTime;	
	private String coach;
	private String classId;
	private String sectionId;
	private Date date;	
	private String userId;
	private String userName;	
	//private Date enrolledDate;	
	
	
	public String getInsertedBy() {
		return insertedBy;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonProperty("insertedBy")
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	@JsonProperty("insertedTime")
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getClassId() {
		return classId;
	}
	@JsonProperty("classId")
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSectionId() {
		return sectionId;
	}
	@JsonProperty("sectionId")
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public Date getDate() {
		return date;
	}
	@JsonProperty("date")
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	@JsonProperty("userId")
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getActivityCode() {
		return activityCode;
	}
	@JsonProperty("activityCode")
	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}
	public String getActivityDescription() {
		return activityDescription;
	}
	@JsonProperty("activityDescription")
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	public String getCoach() {
		return coach;
	}
	@JsonProperty("coach")
	public void setCoach(String coach) {
		this.coach = coach;
	}
		
}