package com.set.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentActivityDto implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8357167123383812281L;
	private String StudentId;	
	private String StudentFirstName;
	private String StudentLastName;
	private String StudentMiddleName;	
	private int ActivityCode;
	private Date EnrolledDate;
	private Date DeactivateDate;
	private String InsertedBy;
	private String UpdatedBy;
	private String ActivityDescription;	
	private String message;
	private List <String> StudentList;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getActivityDescription() {
		return ActivityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		ActivityDescription = activityDescription;
	}
	public List<String> getStudentList() {
		return StudentList;
	}
	@JsonProperty("StudentList")
	public void setStudentList(List<String> studentList) {
		StudentList = studentList;
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
	public String getInsertedBy() {
		return InsertedBy;
	}
	@JsonProperty("InsertedBy")
	public void setInsertedBy(String insertedBy) {
		InsertedBy = insertedBy;
	}
	public String getUpdatedBy() {
		return UpdatedBy;
	}
	@JsonProperty("UpdatedBy")
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
	public String getStudentId() {
		return StudentId;
	}
	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getStudentFirstName() {
		return StudentFirstName;
	}
	@JsonProperty("StudentFirstName")
	public void setStudentFirstName(String studentFirstName) {
		StudentFirstName = studentFirstName;
	}
	public String getStudentLastName() {
		return StudentLastName;
	}
	@JsonProperty("StudentLastName")
	public void setStudentLastName(String studentLastName) {
		StudentLastName = studentLastName;
	}
	public String getStudentMiddleName() {
		return StudentMiddleName;
	}
	@JsonProperty("StudentMiddleName")
	public void setStudentMiddleName(String studentMiddleName) {
		StudentMiddleName = studentMiddleName;
	}
	
}
