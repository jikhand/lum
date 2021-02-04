/**
 * 
 */
package com.set.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author set-0018
 *
 */
public class StudentListingSubmittedAssignmentDto {
	   private String AssignmentReviewComment;
	   private int AssignmentSubmissionMark;
	   private String AssignmentSubject;
	   private Date AssignmentDueDate;
	   private Date AssignmentCreatedDate;
	   private String Id;
	   private String SubjectName;
	   private String Status;

	   @javax.persistence.Transient
	   private String Message;

	public String getAssignmentReviewComment() {
		return AssignmentReviewComment;
	}

	@JsonProperty("AssignmentReviewComment")
	public void setAssignmentReviewComment(String assignmentReviewComment) {
		AssignmentReviewComment = assignmentReviewComment;
	}

	public int getAssignmentSubmissionMark() {
		return AssignmentSubmissionMark;
	}
	@JsonProperty("AssignmentSubmissionMark")
	public void setAssignmentSubmissionMark(int assignmentSubmissionMark) {
		AssignmentSubmissionMark = assignmentSubmissionMark;
	}

	public String getAssignmentSubject() {
		return AssignmentSubject;
	}
	@JsonProperty("AssignmentSubject")
	public void setAssignmentSubject(String assignmentSubject) {
		AssignmentSubject = assignmentSubject;
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

	public String getId() {
		return Id;
	}
	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public String getSubjectName() {
		return SubjectName;
	}
	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getStatus() {
		return Status;
	}
	@JsonProperty("Status")
	public void setStatus(String status) {
		Status = status;
	}

	public String getMessage() {
		return Message;
	}
	@JsonProperty("Message")
	public void setMessage(String message) {
		Message = message;
	}
	   
	   
}
