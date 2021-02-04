package com.set.model;

import java.io.Serializable;
import java.util.List;

public class AssignmentMasterDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 715780502311754044L;
	private List<AssignmentMaster> assignmentMaster;
	
	public List<AssignmentMaster> getAssignmentMaster() {
		return assignmentMaster;
	}
	public void setAssignmentMaster(List<AssignmentMaster> assignmentMaster) {
		this.assignmentMaster = assignmentMaster;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@javax.persistence.Transient
	private String message;
}
