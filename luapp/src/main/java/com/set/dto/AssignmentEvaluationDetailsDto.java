package com.set.dto;

import java.io.Serializable;
import java.util.List;

public class AssignmentEvaluationDetailsDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8432945367756675538L;
	private List<AssignmentEvaluationDetailsDto> Assignments;
	private int count;
	private String message;
	public List<AssignmentEvaluationDetailsDto> getAssignments() {
		return Assignments;
	}
	public void setAssignments(List<AssignmentEvaluationDetailsDto> assignments) {
		Assignments = assignments;
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
