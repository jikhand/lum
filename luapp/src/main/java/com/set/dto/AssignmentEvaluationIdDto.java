package com.set.dto;

import java.io.Serializable;

public class AssignmentEvaluationIdDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5684446681246218958L;
	private String studentId;
	private String assignId;

	public AssignmentEvaluationIdDto(String studentId, String assignId) {
		this.studentId = studentId;
		this.assignId = assignId;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getAssignId() {
		return assignId;
	}

}
