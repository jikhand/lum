package com.set.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextBookDetails implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2691918032704934674L;

	private List<TextBookMaster> TextBooks;
	
	public List<TextBookMaster> getTextBooks() {
		return TextBooks;
	}
	@JsonProperty("TextBooks")
	public void setTextBooks(List<TextBookMaster> TextBooks) {
		this.TextBooks = TextBooks;
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