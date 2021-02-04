package com.set.model;

import java.io.Serializable;
import java.util.List;

public class ExamDetailDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843816L;
	
	private List<ExamDetail> examDetail;
	private int count;
	@javax.persistence.Transient
	private String message;
	
	public List<ExamDetail> getExamDetail() {
		return examDetail;
	}
	public void setExamDetail(List<ExamDetail> examDetail) {
		this.examDetail = examDetail;
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