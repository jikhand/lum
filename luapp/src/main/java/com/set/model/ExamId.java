package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamId{
	/**
	 * 
	 */
	private String ExamId;

	public String getExamId() {
		return ExamId;
	}

	@JsonProperty("TestId")
	public void setExamId(String examId) {
		ExamId = examId;
	}
	
}
