package com.set.model;

import java.io.Serializable;
import java.util.List;

public class StudentMasterDetils implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6222293613315512523L;
	private List<StudentMaster> ClassStudents;
	private int count;
	@javax.persistence.Transient
	private String message;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMessage() {
		return message;
	}

	public List<StudentMaster> getStudentMasterList() {
		return ClassStudents;
	}

	public void setStudentMasterList(List<StudentMaster> studentMasterList) {
		this.ClassStudents = studentMasterList;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
