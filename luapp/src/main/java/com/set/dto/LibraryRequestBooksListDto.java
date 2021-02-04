package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryRequestBooksListDto implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6854234526226259896L;

	private List<LibraryRequestDto> LibraryRequests;
	
	private int Count;
	
	@javax.persistence.Transient
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<LibraryRequestDto> getLibraryRequests() {
		return LibraryRequests;
	}
	
	@JsonProperty("LibraryRequests")
	public void setLibraryRequests(List<LibraryRequestDto> libraryrequest) {
		this.LibraryRequests = libraryrequest;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}
	
}
