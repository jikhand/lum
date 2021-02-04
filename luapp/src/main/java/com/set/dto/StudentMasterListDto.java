package com.set.dto;

import java.io.Serializable;
import java.util.List;

public class StudentMasterListDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6222293613315512523L;
	private List<StudentMasterDetailsDto> ClassStudents;
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

	public List<StudentMasterDetailsDto> getStudentMasterList() {
		return ClassStudents;
	}

	public void setStudentMasterList(List<StudentMasterDetailsDto> studentMasterList) {
		this.ClassStudents = studentMasterList;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
