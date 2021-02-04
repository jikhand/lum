package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student{
	/**
	 * 
	 */
	private String StudentId;

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	
}
