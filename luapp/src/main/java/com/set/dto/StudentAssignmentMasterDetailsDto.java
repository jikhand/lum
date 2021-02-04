package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentAssignmentMasterDetailsDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8369722904745096153L;
	private List<StudentAssignmentMasterDto> Assignments;
	private String Message = "";
	private int count = 0;
	
	public List<StudentAssignmentMasterDto> getAssignments() {
		return Assignments;
	}
	@JsonProperty("Assignments")
	public void setAssignments(List<StudentAssignmentMasterDto> assignments) {
		Assignments = assignments;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
