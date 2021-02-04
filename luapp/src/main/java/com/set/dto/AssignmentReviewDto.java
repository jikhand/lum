package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentReviewDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3708895497054373134L;
	private String AssignmentId;
	private String StudentId;
	private String Status;
	private String AssignmentSubmissionMark;
	private String AssignmentReviewComment;
	public String getAssignmentId() {
		return AssignmentId;
	}
	@JsonProperty("AssignmentId")
	public void setAssignmentId(String assignmentId) {
		AssignmentId = assignmentId;
	}
	public String getStudentId() {
		return StudentId;
	}
	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getStatus() {
		return Status;
	}
	@JsonProperty("Status")
	public void setStatus(String status) {
		Status = status;
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
}
