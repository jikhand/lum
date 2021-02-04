package com.set.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SportsActivityDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5261357021162566633L;

	private List<SportsActivity> SportsActivitys;
	
	public List<SportsActivity> getSportsActivitys() {
		return SportsActivitys;
	}
	@JsonProperty("SportsActivity")
	public void setSportsActivitys(List<SportsActivity> SportsActivitys) {
		this.SportsActivitys = SportsActivitys;
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