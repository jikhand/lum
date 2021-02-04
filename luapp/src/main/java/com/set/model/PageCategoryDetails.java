package com.set.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageCategoryDetails implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3417906499811652870L;

	private List<PageCategory> PageCategoryDetails;
	
	public List<PageCategory> getPageCategoryDetails() {
		return PageCategoryDetails;
	}
	@JsonProperty("PageCategoryDetails")
	public void setPageCategoryDetails(List<PageCategory> pageCategoryDetails) {
		this.PageCategoryDetails = pageCategoryDetails;
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