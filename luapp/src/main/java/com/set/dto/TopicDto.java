package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class TopicDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 406639065142140710L;
	private String TopicId;
	private String TopicName;
		
	
	public String getTopicId() {
		return TopicId;
	}

	@JsonProperty("TopicId")
	public void setTopicId(String topicId) {
		TopicId = topicId;
	}

	public String getTopicName() {
		return TopicName;
	}

	@JsonProperty("TopicName")
	public void setTopicName(String topicName) {
		TopicName = topicName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
