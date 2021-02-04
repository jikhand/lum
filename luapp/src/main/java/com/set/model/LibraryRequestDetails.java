package com.set.model;

import java.io.Serializable;
import java.util.List;

public class LibraryRequestDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8829238827097094914L;

	private List<LibraryRequest> LibraryRequest;
	
	public List<LibraryRequest> getLibraryRequest() {
		return LibraryRequest;
	}
	public void setLibraryRequest(List<LibraryRequest> libraryRequest) {
		this.LibraryRequest = libraryRequest;
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