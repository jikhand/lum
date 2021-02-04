package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3708895497054373134L;
	private String Id;
	private String AssignmentSubject;
	private int AssignmentMark;
	private String AssignmentDescription;
	private String AssignmentAttachment;
	private Date AssignmentDueDate;
	private Date AssignmentCreatedDate;
	private String AssignmentType;
	private String TeacherId;
	private String ClassId;
	private String SubjectId;
	private String SectionId;
	private String PageTypeId;

	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

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

	@JsonProperty("AssignmentTypeId")
	public void setAssignmentType(String assignmentType) {
		AssignmentType = assignmentType;
	}

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getSectionId() {
		return SectionId;
	}

	@JsonProperty("SectionId")
	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}

	public String getPageTypeId() {
		return PageTypeId;
	}

	@JsonProperty("PageTypeId")
	public void setPageTypeId(String pageTypeId) {
		PageTypeId = pageTypeId;
	}

	public String getTeacherId() {
		return TeacherId;
	}

	@JsonProperty("TeacherId")
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

}
