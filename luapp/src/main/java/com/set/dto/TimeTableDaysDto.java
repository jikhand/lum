package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTableDaysDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4589943146642055329L;
	
	private String DayID;
	private String DayNAME;
	private TimeTableDto TimeTableData;
	private String message;
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDayID() {
		return DayID;
	}
	@JsonProperty("DayID")
	public void setDayID(String dayID) {
		DayID = dayID;
	}
	public String getDayNAME() {
		return DayNAME;
	}
	@JsonProperty("DayNAME")
	public void setDayNAME(String dayNAME) {
		DayNAME = dayNAME;
	}
	public TimeTableDto getTimeTableData() {
		return TimeTableData;
	}
	@JsonProperty("TimeTableData")
	public void setTimeTableData(TimeTableDto timeTableData) {
		TimeTableData = timeTableData;
	}	
}