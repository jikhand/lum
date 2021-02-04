package com.set.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectResourceDetails implements Serializable {
	private static final long serialVersionUID = 4436302371793843793L;
	private List<Map<String, String>> SubjectResourceList;

	public List<Map<String, String>> getSubjectResource() {
		return SubjectResourceList;
	}

	@JsonProperty("SubjectResourceList")
	public void setSubjectResource(List<Map<String, String>> hm) {
		this.SubjectResourceList = (List<Map<String, String>>) hm;
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
