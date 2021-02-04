package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Teacher{
	/**
	 * 
	 */
	private String TeacherId;

	public String getTeacherId() {
		return TeacherId;
	}

	@JsonProperty("TeacherId")
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}
	
}
