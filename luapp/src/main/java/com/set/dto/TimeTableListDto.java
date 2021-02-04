package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTableListDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4436302371793843799L;
	
	private List<TimeTableDto> TimeTableListData;
	private int Count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<TimeTableDto> getTimeTableListData() {
		return TimeTableListData;
	}
	@JsonProperty("TimeTableData")
	public void setTimeTableListData(List<TimeTableDto> timeTableListData) {
		this.TimeTableListData = timeTableListData;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}
	
}