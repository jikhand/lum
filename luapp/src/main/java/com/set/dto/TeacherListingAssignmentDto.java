/**
 * 
 */
package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author set-0018
 *
 */
public class TeacherListingAssignmentDto implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = -7091389901377719482L;
	private String AssignmentSubject;
	   private int AssignmentMark;
	   private String AssignmentDescription;
	   private String AssignmentAttachment;
	   private Date AssignmentDueDate;
	   private Date AssignmentCreatedDate;
	   private String AssignmentType;
	   private String PageTypeName;
	   private String CassName;
	   private String SubjectName;
	   private String Id;
	   private String SectionName;

	   @javax.persistence.Transient
	   private String Message;

	public String getAssignmentSubject() {
		return AssignmentSubject;
	}
	@JsonProperty("AssignmentSubject")
	public void setAssignmentSubject(String assignmentSubject) {
		AssignmentSubject = assignmentSubject;
	}

	public int getAssignmentMark() {
		return AssignmentMark;
	}
	@JsonProperty("AssignmentMark")
	public void setAssignmentMark(int assignmentMark) {
		AssignmentMark = assignmentMark;
	}

	public String getAssignmentDescription() {
		return AssignmentDescription;
	}
	@JsonProperty("AssignmentDescription")
	public void setAssignmentDescription(String assignmentDescription) {
		AssignmentDescription = assignmentDescription;
	}

	public String getAssignmentAttachment() {
		return AssignmentAttachment;
	}
	@JsonProperty("AssignmentAttachment")
	public void setAssignmentAttachment(String assignmentAttachment) {
		AssignmentAttachment = assignmentAttachment;
	}

	public Date getAssignmentDueDate() {
		return AssignmentDueDate;
	}
	@JsonProperty("AssignmentDueDate")
	public void setAssignmentDueDate(Date assignmentDueDate) {
		AssignmentDueDate = assignmentDueDate;
	}

	public Date getAssignmentCreatedDate() {
		return AssignmentCreatedDate;
	}
	@JsonProperty("AssignmentCreatedDate")
	public void setAssignmentCreatedDate(Date assignmentCreatedDate) {
		AssignmentCreatedDate = assignmentCreatedDate;
	}

	public String getAssignmentType() {
		return AssignmentType;
	}
	@JsonProperty("AssignmentType")
	public void setAssignmentType(String assignmentType) {
		AssignmentType = assignmentType;
	}

	public String getPageTypeName() {
		return PageTypeName;
	}
	@JsonProperty("PageTypeName")
	public void setPageTypeName(String pageTypeName) {
		PageTypeName = pageTypeName;
	}

	public String getCassName() {
		return CassName;
	}
	@JsonProperty("CassName")
	public void setCassName(String cassName) {
		CassName = cassName;
	}

	public String getSubjectName() {
		return SubjectName;
	}
	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getId() {
		return Id;
	}
	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public String getSectionName() {
		return SectionName;
	}
	@JsonProperty("SectionName")
	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}

	public String getMessage() {
		return Message;
	}
	@JsonProperty("Message")
	public void setMessage(String message) {
		Message = message;
	}
	   
}
