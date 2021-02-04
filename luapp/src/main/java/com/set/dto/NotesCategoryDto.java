package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class NotesCategoryDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3160922576219379289L;
	private String NotesCategoryId;
	private String CategoryThumbinal;
	private String UnitTopicId;
	private String TopicName;
	private String PageType;
	
	public String getNotesCategoryId() {
		return NotesCategoryId;
	}

	@JsonProperty("NotesCategoryId")
	public void setNotesCategoryId(String notesCategoryId) {
		NotesCategoryId = notesCategoryId;
	}

	public String getCategoryThumbinal() {
		return CategoryThumbinal;
	}

	@JsonProperty("CategoryThumbinal")
	public void setCategoryThumbinal(String categoryThumbinal) {
		CategoryThumbinal = categoryThumbinal;
	}

	public String getUnitTopicId() {
		return UnitTopicId;
	}

	@JsonProperty("UnitTopicId")
	public void setUnitTopicId(String unitTopicId) {
		UnitTopicId = unitTopicId;
	}

	public String getTopicName() {
		return TopicName;
	}

	@JsonProperty("TopicName")
	public void setTopicName(String topicName) {
		TopicName = topicName;
	}

	public String getPageType() {
		return PageType;
	}

	@JsonProperty("PageType")
	public void setPageType(String pageType) {
		PageType = pageType;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
