package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmittedAssignmentEvaluationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6642565319251621675L;
	public String Id;
	private String StudentId;
	private String ClassId;
	private String SectionId;
	private String AssignmentId;
	private String AssignmentSubject;
	private String AssignmentSubmissionMark;
	private String AssignmentSubmissionAttachment;
	private String Status;
	private Date AssignmentDueDate;
	private String AssignmentReviewComment;
	private Date AssignmentSubmittedDate;
	private String StudentFirstName;
	private String StudentMiddleName;
	private String StudentLastName;
	private String StudentProfileImage;
	private String Message;

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

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
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

	public String getAssignmentId() {
		return AssignmentId;
	}

	@JsonProperty("AssignmentId")
	public void setAssignmentId(String assignmentId) {
		AssignmentId = assignmentId;
	}

	public String getAssignmentSubmissionAttachment() {
		return AssignmentSubmissionAttachment;
	}

	@JsonProperty("AssignmentSubmissionAttachment")
	public void setAssignmentSubmissionAttachment(String assignmentSubmissionAttachment) {
		AssignmentSubmissionAttachment = assignmentSubmissionAttachment;
	}

	public Date getAssignmentSubmittedDate() {
		return AssignmentSubmittedDate;
	}

	@JsonProperty("AssignmentSubmittedDate")
	public void setAssignmentSubmittedDate(Date assignmentSubmittedDate) {
		AssignmentSubmittedDate = assignmentSubmittedDate;
	}

	public String getStudentFirstName() {
		return StudentFirstName;
	}

	@JsonProperty("StudentFirstName")
	public void setStudentFirstName(String studentFirstName) {
		StudentFirstName = studentFirstName;
	}

	public String getStudentMiddleName() {
		return StudentMiddleName;
	}

	@JsonProperty("StudentMiddleName")
	public void setStudentMiddleName(String studentMiddleName) {
		StudentMiddleName = studentMiddleName;
	}

	public String getStudentLastName() {
		return StudentLastName;
	}

	@JsonProperty("StudentLastName")
	public void setStudentLastName(String studentLastName) {
		StudentLastName = studentLastName;
	}

	public String getStudentProfileImage() {
		return StudentProfileImage;
	}

	@JsonProperty("StudentProfileImage")
	public void setStudentProfileImage(String studentProfileImage) {
		StudentProfileImage = studentProfileImage;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
}
