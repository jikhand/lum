package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamTypeDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8346093205645306945L;
	private String ExamType;
	private String ExamTypeDescription;
		
	public String getExamType() {
		return ExamType;
	}

	public void setExamType(String examType) {
		ExamType = examType;
	}

	@JsonProperty("ExamType")
	public String getExamTypeDescription() {
		return ExamTypeDescription;
	}

	@JsonProperty("ExamTypeDescription")
	public void setExamTypeDescription(String examTypeDescription) {
		ExamTypeDescription = examTypeDescription;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
