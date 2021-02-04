package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentEvaluationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6642565319251621675L;
	public String Id;
	private Date AssignmentCreatedDate;
	private Date AssignmentDueDate;
	private String Status;
	private String AssignmentSubject;
	private String AssignmentSubmissionMark;
	private String AssignmentReviewComment;
	private String AssignmentAttachment;
	private String NameOfReviewer;
	private String SubjectName;
	private String ClassName;
	private String StudentName;
	private String StudentId;
	private String SectionName;
	private String Message;

	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public Date getAssignmentCreatedDate() {
		return AssignmentCreatedDate;
	}

	@JsonProperty("AssignmentCreatedDate")
	public void setAssignmentCreatedDate(Date assignmentCreatedDate) {
		AssignmentCreatedDate = assignmentCreatedDate;
	}

	public Date getAssignmentDueDate() {
		return AssignmentDueDate;
	}

	@JsonProperty("AssignmentDueDate")
	public void setAssignmentDueDate(Date assignmentDueDate) {
		AssignmentDueDate = assignmentDueDate;
	}

	public String getStatus() {
		return Status;
	}

	@JsonProperty("Status")
	public void setStatus(String status) {
		Status = status;
	}

	public String getAssignmentSubject() {
		return AssignmentSubject;
	}

	@JsonProperty("AssignmentSubject")
	public void setAssignmentSubject(String assignmentSubject) {
		AssignmentSubject = assignmentSubject;
	}

	public String getAssignmentSubmissionMark() {
		return AssignmentSubmissionMark;
	}

	@JsonProperty("AssignmentSubmissionMark")
	public void setAssignmentSubmissionMark(String assignmentSubmissionMark) {
		AssignmentSubmissionMark = assignmentSubmissionMark;
	}

	public String getAssignmentReviewComment() {
		return AssignmentReviewComment;
	}

	@JsonProperty("AssignmentReviewComment")
	public void setAssignmentReviewComment(String assignmentReviewComment) {
		AssignmentReviewComment = assignmentReviewComment;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getClassName() {
		return ClassName;
	}

	@JsonProperty("ClassName")
	public void setClassName(String className) {
		ClassName = className;
	}

	public String getStudentName() {
		return StudentName;
	}

	@JsonProperty("StudentName")
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getSectionName() {
		return SectionName;
	}

	@JsonProperty("SectionName")
	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}

	public String getNameOfReviewer() {
		return NameOfReviewer;
	}

	@JsonProperty("NameOfReviewer")
	public void setNameOfReviewer(String nameOfReviewer) {
		NameOfReviewer = nameOfReviewer;
	}

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public String getAssignmentAttachment() {
		return AssignmentAttachment;
	}
	@JsonProperty("AssignmentAttachment")
	public void setAssignmentAttachment(String assignmentAttachment) {
		AssignmentAttachment = assignmentAttachment;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
}
