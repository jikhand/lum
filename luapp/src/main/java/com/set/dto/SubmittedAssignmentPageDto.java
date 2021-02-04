package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmittedAssignmentPageDto implements Serializable{

	private static final long serialVersionUID = 2357716479746642740L;
	private String PageNumber;
	private String AssignmentSubmissionDescription;

	public String getPageNumber() {
		return PageNumber;
	}
	@JsonProperty("PageNumber")
	public void setPageNumber(String pageNumber) {
		PageNumber = pageNumber;
	}

	public String getAssignmentSubmissionDescription() {
		return AssignmentSubmissionDescription;
	}
	@JsonProperty("AssignmentSubmissionDescription")
	public void setAssignmentSubmissionDescription(String assignmentSubmissionDescription) {
		AssignmentSubmissionDescription = assignmentSubmissionDescription;
	}

}
