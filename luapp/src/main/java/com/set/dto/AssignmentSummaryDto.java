package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentSummaryDto {
	private String SubmittedAssignmentsCount = "";
	private String RedoAssignmentsCount = "";
	private String ApprovedAssignmentsCount = "";
	private String RejectedAssignmentsCount = "";
	private List<SubmittedAssignmentDto> StudentSubmittedAssignments;

	public String getSubmittedAssignmentsCount() {
		return SubmittedAssignmentsCount;
	}
	@JsonProperty("SubmittedAssignmentsCount")
	public void setSubmittedAssignmentsCount(String submittedAssignmentsCount) {
		SubmittedAssignmentsCount = submittedAssignmentsCount;
	}

	public String getRedoAssignmentsCount() {
		return RedoAssignmentsCount;
	}
	@JsonProperty("RedoAssignmentsCount")
	public void setRedoAssignmentsCount(String redoAssignmentsCount) {
		RedoAssignmentsCount = redoAssignmentsCount;
	}

	public String getApprovedAssignmentsCount() {
		return ApprovedAssignmentsCount;
	}
	@JsonProperty("ApprovedAssignmentsCount")
	public void setApprovedAssignmentsCount(String approvedAssignmentsCount) {
		ApprovedAssignmentsCount = approvedAssignmentsCount;
	}

	public String getRejectedAssignmentsCount() {
		return RejectedAssignmentsCount;
	}
	@JsonProperty("RejectedAssignmentsCount")
	public void setRejectedAssignmentsCount(String rejectedAssignmentsCount) {
		RejectedAssignmentsCount = rejectedAssignmentsCount;
	}

	public List<SubmittedAssignmentDto> getStudentSubmittedAssignments() {
		return StudentSubmittedAssignments;
	}
	@JsonProperty("StudentSubmittedAssignments")
	public void setStudentSubmittedAssignments(List<SubmittedAssignmentDto> studentSubmittedAssignments) {
		StudentSubmittedAssignments = studentSubmittedAssignments;
	}
}
