package com.set.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentsActivityDetails implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7156533640764709239L;

	private List<StudentActivity> StudentActivitys;
	
	public List<StudentActivity> getStudentActivitys() {
		return StudentActivitys;
	}
	@JsonProperty("StudentActivity")
	public void setStudentActivitys(List<StudentActivity> StudentActivitys) {
		this.StudentActivitys = StudentActivitys;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	private int count;
	
	@javax.persistence.Transient
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}