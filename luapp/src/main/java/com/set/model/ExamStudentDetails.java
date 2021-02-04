package com.set.model;

import java.io.Serializable;
import java.util.List;

public class ExamStudentDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<ExamStudent> examStudent;
	public List<ExamStudent> getExamStudent() {
		return examStudent;
	}
	public void setExamStudent(List<ExamStudent> examStudent) {
		this.examStudent = examStudent;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
