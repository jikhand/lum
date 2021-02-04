package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentMasterDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8719139076861369656L;
	private String AssignmentAttachment;
	private Date AssignmentCreatedDate;
	private String AssignmentDescription = "";
	private Date AssignmentDueDate;
	private int AssignmentMark;
	private String AssignmentSubject;
	private String AssignmentType = "1";
	private String ClassName;
	private String Id;
	private String PageTypeName; //= "Ruled";
	private String SubjectId;
	private String SubjectName;
	private String ClassId;
	private String SectionId;
	@Transient
	private String message;
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getSectionId() {
		return SectionId;
	}

	@JsonProperty("SectionId")
	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}

	public Date getAssignmentCreatedDate() {
		return AssignmentCreatedDate;
	}

	@JsonProperty("AssignmentCreatedDate")
	public void setAssignmentCreatedDate(Date assignmentCreatedDate) {
		AssignmentCreatedDate = assignmentCreatedDate;
	}

	public String getAssignmentDescription() {
		return AssignmentDescription;
	}

	@JsonProperty("AssignmentDescription")
	public void setAssignmentDescription(String assignmentDescription) {
		AssignmentDescription = assignmentDescription;
	}

	public String getAssignmentType() {
		return AssignmentType;
	}

	@JsonProperty("AssignmentType")
	public void setAssignmentType(String assignmentType) {
		AssignmentType = assignmentType;
	}

	public String getClassName() {
		return ClassName;
	}

	@JsonProperty("ClassName")
	public void setClassName(String className) {
		ClassName = className;
	}

	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public Date getAssignmentDueDate() {
		return AssignmentDueDate;
	}

	@JsonProperty("AssignmentDueDate")
	public void setAssignmentDueDate(Date assignmentDueDate) {
		AssignmentDueDate = assignmentDueDate;
	}

	public String getPageTypeName() {
		return PageTypeName;
	}

	@JsonProperty("PageTypeName")
	public void setPageTypeName(String pageTypeName) {
		PageTypeName = pageTypeName;
	}

	public int getAssignmentMark() {
		return AssignmentMark;
	}

	@JsonProperty("AssignmentMark")
	public void setAssignmentMark(int assignmentMark) {
		AssignmentMark = assignmentMark;
	}

	public String getAssignmentSubject() {
		return AssignmentSubject;
	}

	@JsonProperty("AssignmentTitle")
	public void setAssignmentSubject(String assignmentSubject) {
		AssignmentSubject = assignmentSubject;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getAssignmentAttachment() {
		return AssignmentAttachment;
	}

	@JsonProperty("AssignmentAttachment")
	public void setAssignmentAttachment(String assignmentAttachment) {
		AssignmentAttachment = assignmentAttachment;
	}

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}
}

