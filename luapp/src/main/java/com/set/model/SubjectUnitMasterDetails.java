package com.set.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectUnitMasterDetails implements Serializable {
	private static final long serialVersionUID = 4436302371793843793L;
	private List<SubjectUnitMaster> SubjectUnitMasterList;

	public List<SubjectUnitMaster> getSubjectUnitMaster() {
		return SubjectUnitMasterList;
	}

	@JsonProperty("SubjectUnitMasterList")
	public void setSubjectUnitMaster(List<SubjectUnitMaster> subjectUnitMaster) {
		this.SubjectUnitMasterList = subjectUnitMaster;
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
