package com.set.model;

import java.io.Serializable;
import java.util.List;

public class AssignmentPagesDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 715780502311754044L;
	private List<AssignmentPages> assignmentPages;

	public List<AssignmentPages> getAssignmentPages() {
		return assignmentPages;
	}

	public void setAssignmentPages(List<AssignmentPages> assignmentPages) {
		this.assignmentPages = assignmentPages;
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
