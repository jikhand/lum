package com.set.model;

import java.io.Serializable;
import java.util.List;

public class ExamTypeDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6222293613315512523L;
	private List<ExamType> examType;
	
	public List<ExamType> getExamType() {
		return examType;
	}
	public void setExamType(List<ExamType> examType) {
		this.examType = examType;
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
