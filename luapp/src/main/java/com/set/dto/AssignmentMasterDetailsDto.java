package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentMasterDetailsDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8369722904745096153L;
	private List<AssignmentMasterDto> Assignments;
	private String Message = "";
	private int count = 0;
	
	public List<AssignmentMasterDto> getAssignments() {
		return Assignments;
	}
	@JsonProperty("Assignments")
	public void setAssignments(List<AssignmentMasterDto> assignments) {
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
