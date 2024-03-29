package com.set.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTableDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4436302371793843799L;
	
	private List<TimeTable> TimeTables;
	private int Count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<TimeTable> getTimeTables() {
		return TimeTables;
	}
	@JsonProperty("TimeTables")
	public void setTimeTables(List<TimeTable> timeTables) {
		this.TimeTables = timeTables;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}
	
}