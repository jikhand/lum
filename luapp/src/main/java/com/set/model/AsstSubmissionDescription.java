package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AsstSubmissionDescription {
	private String AssignmentImageUrl;
	private String Pagenumber;

	public String getAssignmentImageUrl() {
		return AssignmentImageUrl;
	}

	@JsonProperty("AssignmentImageUrl")
	public void setAssignmentSubmissionDescription(String assignmentImageUrl) {
		AssignmentImageUrl = assignmentImageUrl;
	}

	public String getPageNumber() {
		return Pagenumber;
	}

	@JsonProperty("Pagenumber")
	public void setPageNumber(String pageNumber) {
		Pagenumber = pageNumber;
	}
}
