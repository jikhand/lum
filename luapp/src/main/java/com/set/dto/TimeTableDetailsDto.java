package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTableDetailsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4200777979990626065L;
	private List<TimeTableDto> TimeTableList;
	private int count;
	@javax.persistence.Transient
	private String message;

	public List<TimeTableDto> getTimeTableList() {
		return TimeTableList;
	}

	@JsonProperty("TimeTableList")
	public void setTimeTableList(List<TimeTableDto> timeTabels) {
		TimeTableList = timeTabels;
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
}
