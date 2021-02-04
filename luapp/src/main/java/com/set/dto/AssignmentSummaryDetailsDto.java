package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentSummaryDetailsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191642151409389333L;
	private AssignmentSummaryDto Assignments;
	private String Message;

	public AssignmentSummaryDto getAssignments() {
		return Assignments;
	}

	@JsonProperty("Assignments")
	public void setAssignments(AssignmentSummaryDto assignments) {
		Assignments = assignments;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

}
