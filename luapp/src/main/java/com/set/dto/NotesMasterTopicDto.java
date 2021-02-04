package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotesMasterTopicDto implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3817768821390079974L;
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
}