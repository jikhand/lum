package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class StudentNotesDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9093637053778346721L;
	private String PageNumber;
	private String NotesImageUrl;
	
	public String getPageNumber() {
		return PageNumber;
	}

	@JsonProperty("PageNumber")
	public void setPageNumber(String pageNumber) {
		PageNumber = pageNumber;
	}

	public String getNotesImageUrl() {
		return NotesImageUrl;
	}

	@JsonProperty("NotesImageUrl")
	public void setNotesImageUrl(String notesImageUrl) {
		NotesImageUrl = notesImageUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
