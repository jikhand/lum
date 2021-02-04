package com.set.model;

import java.io.Serializable;
import java.util.List;

public class AssignmentDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843802L;
	
	private List<Assignment> assignment;
	private int count;
	@javax.persistence.Transient
	private String message;
	
	public List<Assignment> getAssignment() {
		return assignment;
	}
	public void setAssignment(List<Assignment> assignment) {
		this.assignment = assignment;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}