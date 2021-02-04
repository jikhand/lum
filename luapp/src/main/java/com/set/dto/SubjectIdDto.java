package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectIdDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7908464413108933153L;
	private String SubjectId;
	private String SubjectName;

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

}
