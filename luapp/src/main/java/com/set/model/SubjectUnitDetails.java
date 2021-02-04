package com.set.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectUnitDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<SubjectUnit> SubjectUnit;
	public List<SubjectUnit> getSubjectUnit() {
		return SubjectUnit;
	}
	@JsonProperty("SubjectUnit")
	public void setSubjectUnit(List<SubjectUnit> subjectUnit) {
		this.SubjectUnit = subjectUnit;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}
	private int Count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
