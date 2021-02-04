package com.set.model;

import java.io.Serializable;
import java.util.List;

public class SubjectMasterDetils implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6222293613315512523L;
	private List<SubjectMaster> subjectMaster;
	
	public List<SubjectMaster> getSubjectMaster() {
		return subjectMaster;
	}
	public void setSubjectMaster(List<SubjectMaster> subjectMaster) {
		this.subjectMaster = subjectMaster;
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
