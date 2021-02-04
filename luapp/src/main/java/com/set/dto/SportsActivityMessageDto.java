package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SportsActivityMessageDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4436302371793843799L;
	
	//private List<SportsActivityTeacherDto> SportsActivityData;
//	private String DayId;
//	private String DayName;
	private String message;
	private String code;
	private SportsActivityTeacherDto SportsActivityData;
	
//	public List<SportsActivityTeacherDto> getSportsActivityData() {
//		return SportsActivityData;
//	}
//	@JsonProperty("SportsActivityData")
//	public void setSportsActivityData(List<SportsActivityTeacherDto> sportsActivityData) {
//		this.SportsActivityData = sportsActivityData;
//	}
	
	public SportsActivityTeacherDto getTimeTableData() {
		return SportsActivityData;
	}
	@JsonProperty("SportsActivityData")
	public void setSportsActivityData(SportsActivityTeacherDto sportsActivityData) {
		SportsActivityData = sportsActivityData;
	}		
//	public String getDayId() {
//		return DayId;
//	}
//	@JsonProperty("DayId")
//	public void setDayId(String dayId) {
//		DayId = dayId;
//	}
//	public String getDayName() {
//		return DayName;
//	}
//	@JsonProperty("DayName")
//	public void setDayName(String dayName) {
//		DayName = dayName;
//	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}