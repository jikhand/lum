package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.set.model.StudentActivity;
import com.set.model.TextBookMaster;

public class StudentActivityListDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3678217483194077951L;
	/**
	 * 
	 */
	
	private List<StudentActiDto> StudentActivitys;
	private int Count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<StudentActiDto> getStudentActivitys() {
		return StudentActivitys;
	}
	@JsonProperty("StudentActivity")
	public void setStudentActivitys(List<StudentActiDto> StudentActivitys) {
		this.StudentActivitys = StudentActivitys;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}

}
