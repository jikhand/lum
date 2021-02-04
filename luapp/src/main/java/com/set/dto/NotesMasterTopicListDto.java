package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotesMasterTopicListDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9164728162962884062L;
	private List<NotesMasterTopicDto> NotesMasterTopicDtoData;
	private int Count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<NotesMasterTopicDto> getNotesMasterTopicDtoData() {
		return NotesMasterTopicDtoData;
	}
	@JsonProperty("NotesMasterTopicDtoData")
	public void setNotesMasterTopicDtoData(List<NotesMasterTopicDto> notesMasterTopicDtoData) {
		this.NotesMasterTopicDtoData = notesMasterTopicDtoData;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}
	
}
