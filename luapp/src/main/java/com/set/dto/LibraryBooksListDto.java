package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryBooksListDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4436302371793843799L;
	
	private List<LibraryDto> LibraryBooks;
	
	private int Count;
	
	@javax.persistence.Transient
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<LibraryDto> getLibraryBooks() {
		return LibraryBooks;
	}
	
	@JsonProperty("LibraryBooks")
	public void setLibraryBooks(List<LibraryDto> libraryBooks) {
		this.LibraryBooks = libraryBooks;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}
	
}
