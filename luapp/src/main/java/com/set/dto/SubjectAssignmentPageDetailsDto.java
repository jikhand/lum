package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectAssignmentPageDetailsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4200777979990626065L;
	private List<SubjectMasterDto> AllSubjects;
	private int count;
	@javax.persistence.Transient
	private String message;

	public List<SubjectMasterDto> getSubjects() {
		return AllSubjects;
	}

	@JsonProperty("AllSubjects")
	public void setSubjects(List<SubjectMasterDto> subjects) {
		AllSubjects = subjects;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
