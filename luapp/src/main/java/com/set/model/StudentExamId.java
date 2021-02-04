package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentExamId{
	/**
	 * 
	 */
	private String ExamId;
	private String StudentId;

	public String getExamId() {
		return ExamId;
	}

	@JsonProperty("TestId")
	public void setExamId(String examId) {
		ExamId = examId;
	}

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	
}
