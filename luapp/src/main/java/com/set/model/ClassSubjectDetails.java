package com.set.model;

import java.io.Serializable;
import java.util.List;

public class ClassSubjectDetails  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -304440929246996295L;
	private List<ClassSubject> classSubject;
	
	public List<ClassSubject> getClassSubject() {
		return classSubject;
	}
	public void setClassSubject(List<ClassSubject> classSubject) {
		this.classSubject = classSubject;
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
