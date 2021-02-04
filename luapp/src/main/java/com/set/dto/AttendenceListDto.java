package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendenceListDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3678217483194077951L;
	/**
	 * 
	 */
	
	private List<AttendenceDto> Attendence;
	private int Count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<AttendenceDto> getAttendence() {
		return Attendence;
	}
	@JsonProperty("Attendence")
	public void setAttendence(List<AttendenceDto> attendence) {
		this.Attendence = attendence;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}

}
