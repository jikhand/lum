package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SportsActivityDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3909861579623788789L;
	/**
	 * 
	 */
	
	
	private int activityCode;
	private String activityDescription;
	private String coach;
	private String studentId;	
	private Date enrolledDate;
	
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
	public String getStudentId() {
		return studentId;
	}
	@JsonProperty("studentId")
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Date getEnrolledDate() {
		return enrolledDate;
	}
	@JsonProperty("enrolledDate")
	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}		
}