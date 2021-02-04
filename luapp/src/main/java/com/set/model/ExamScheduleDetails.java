package com.set.model;

import java.io.Serializable;
import java.util.List;

public class ExamScheduleDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<ExamSchedule> examSchedule;
	public List<ExamSchedule> getExamSchedule() {
		return examSchedule;
	}
	public void setExamSchedule(List<ExamSchedule> examSchedule) {
		this.examSchedule = examSchedule;
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
