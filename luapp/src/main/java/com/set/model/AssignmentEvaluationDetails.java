package com.set.model;

import java.io.Serializable;
import java.util.List;
public class AssignmentEvaluationDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843791L;
	private List<AssignmentEvaluation> AssignmentEvaluation;
	
	public List<AssignmentEvaluation> getAssignmentEvaluation() {
		return AssignmentEvaluation;
	}
	public void setAssignmentEvaluation(List<AssignmentEvaluation> assignmentEvaluation) {
		AssignmentEvaluation = assignmentEvaluation;
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
