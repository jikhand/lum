package com.set.model;

import java.io.Serializable;
import java.util.List;

public class StateDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843795L;
	private List<State> state;
	public List<State> getState() {
		return state;
	}
	public void setState(List<State> state) {
		this.state = state;
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
	private int count;
	@javax.persistence.Transient
	private String message;
}