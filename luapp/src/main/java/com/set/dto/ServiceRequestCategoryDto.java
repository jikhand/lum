package com.set.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class ServiceRequestCategoryDto implements Serializable{

	
	private static final long serialVersionUID = 5563553623016387107L;
	private String categoryId;
	private String description;
	private int resolveDays;
	private String categoryType;
	private String parentCategory;
	private String Message;
	
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getResolveDays() {
		return resolveDays;
	}
	public void setResolveDays(int resolveDays) {
		this.resolveDays = resolveDays;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	
	
}
