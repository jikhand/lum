package com.set.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ClassSubjectDetail  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -304440929246996295L;
	private List<Map<String, String>> classSubject;
	
	public List<Map<String, String>> getClassSubject() {
		return classSubject;
	}
	public void setClassSubject(List<Map<String, String>> arrayList) {
		this.classSubject = arrayList;
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
