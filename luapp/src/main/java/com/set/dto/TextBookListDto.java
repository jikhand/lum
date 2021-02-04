package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.set.model.TextBookMaster;

public class TextBookListDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3678217483194077951L;
	/**
	 * 
	 */
	
	private List<TextBookMaster> BookData;
	private int Count;
	private String code;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<TextBookMaster> getBookData() {
		return BookData;
	}
	@JsonProperty("BookData")
	public void setBookData(List<TextBookMaster> bookData) {
		this.BookData = bookData;
	}
	public int getCount() {
		return Count;
	}
	@JsonProperty("Count")
	public void setCount(int count) {
		this.Count = count;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
