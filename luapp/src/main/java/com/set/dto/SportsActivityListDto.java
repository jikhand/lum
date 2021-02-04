package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.set.model.SportsActivity;
import com.set.model.TextBookMaster;

public class SportsActivityListDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6040465691838848455L;
	/**
	 * 
	 */
	
	//private List<SportsActivityDto> SportsData;
	private List<SportsActivityDto> SportsDatas;
	//private int Count;
	@javax.persistence.Transient
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
	public void setMessage(String message) {
		this.message = message;
	}
	
//	public List<SportsActivityDto> getSportsData() {
//		return SportsData;
//	}
//	@JsonProperty("SportsData")
//	public void setSportsData(List<SportsActivityDto> sportsData) {
//		this.SportsData = sportsData;
//	}
//	public int getCount() {
//		return Count;
//	}
//	@JsonProperty("Count")
//	public void setCount(int count) {
//		this.Count = count;
//	}
	public List<SportsActivityDto> getSportsDatas() {
		return SportsDatas;
	}
	@JsonProperty("SportsDatas")
	public void setSportsDatas(List<SportsActivityDto> sportsDatas) {
		SportsDatas = sportsDatas;
	}	

}
