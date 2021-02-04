package com.set.dto;

import java.io.Serializable;
import java.util.List;

public class StudentActivityDtoDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2528170705099930548L;
	private List<StudentActivityDto> studentActivityDto;
	public List<StudentActivityDto> getStudentActivityDto() {
		return studentActivityDto;
	}
	public void setStudentActivityDto(List<StudentActivityDto> studentActivityDto) {
		this.studentActivityDto = studentActivityDto;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	private int Count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
