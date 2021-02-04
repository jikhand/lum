package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmitStudentDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6803101182007224297L;
	private String StudentId;
	private String StudentName;
	private String StudentEmail;
	
	public String getStudentId() {
		return StudentId;
	}
	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	@JsonProperty("StudentName")
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getStudentEmail() {
		return StudentEmail;
	}
	@JsonProperty("StudentEmail")
	public void setStudentEmail(String studentEmail) {
		StudentEmail = studentEmail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
