package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class UnitTopicDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3160922576219379289L;
	private String UnitId;
	private String UnitName;
	private List<TopicDto> TopicList;
	
	
	public String getUnitId() {
		return UnitId;
	}

	@JsonProperty("UnitId")
	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public String getUnitName() {
		return UnitName;
	}

	@JsonProperty("UnitName")
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}
	
	public List<TopicDto> getTopicList() {
		return TopicList;
	}

	@JsonProperty("topicdata")
	public void setTopicList(List<TopicDto> topicList) {
		TopicList = topicList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
