package com.set.model;

import java.io.Serializable;
import java.util.List;

public class LibraryDetails implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9020588781839029291L;

	private List<LibraryBooks> libraryBooks;
	
	public List<LibraryBooks> getLibraryBooks() {
		return libraryBooks;
	}
	public void setLibraryBooks(List<LibraryBooks> libraryBooks) {
		this.libraryBooks = libraryBooks;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	private int count;
	
	@javax.persistence.Transient
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}