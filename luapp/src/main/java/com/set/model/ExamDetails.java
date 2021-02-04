package com.set.model;

import java.io.Serializable;
import java.util.List;

public class ExamDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843817L;
	
	private List<Exam> exam;
	private int count;
	@javax.persistence.Transient
	private String message;
	
	public List<Exam> getExam() {
		return exam;
	}
	public void setExam(List<Exam> exam) {
		this.exam = exam;
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