package com.set.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentActiDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727653067551136455L;
	private String StudentId;		
	private int ActivityCode;
	private Date EnrolledDate;
	private Date DeactivateDate;	
	private String ActivityDescription;
	private String coach;
	
	
	
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public String getActivityDescription() {
		return ActivityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		ActivityDescription = activityDescription;
	}
	public int getActivityCode() {
		return ActivityCode;
	}
	@JsonProperty("ActivityCode")
	public void setActivityCode(int activityCode) {
		ActivityCode = activityCode;
	}
	public Date getEnrolledDate() {
		return EnrolledDate;
	}
	@JsonProperty("EnrolledDate")
	public void setEnrolledDate(Date enrolledDate) {
		EnrolledDate = enrolledDate;
	}
	public Date getDeactivateDate() {
		return DeactivateDate;
	}
	@JsonProperty("DeactivateDate")
	public void setDeactivateDate(Date deactivateDate) {
		DeactivateDate = deactivateDate;
	}	
	public String getStudentId() {
		return StudentId;
	}
	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}	
}
