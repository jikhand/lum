package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentAttendance {
	private String StudentId;
	private String Status;

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

}
