package com.set.model;
import java.io.Serializable;
import java.util.List;

public class UserDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843797L;
	private List<EmployeeMaster> user;
	public List<EmployeeMaster> getUser() {
		return user;
	}
	public void setUser(List<EmployeeMaster> user) {
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
