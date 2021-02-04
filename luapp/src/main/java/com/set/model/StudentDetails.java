package com.set.model;

import java.io.Serializable;
import java.util.List;

public class StudentDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1934003292643430101L;
	private List<StudentMaster> user;
	public List<StudentMaster> getUser() {
		return user;
	}
	public void setUser(List<StudentMaster> user) {
		this.user = user;
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
