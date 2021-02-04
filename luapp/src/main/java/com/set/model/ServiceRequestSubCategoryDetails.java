package com.set.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
public class ServiceRequestSubCategoryDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843791L;
	private List<Map<String, String>> ServiceRequestCategory;
	
	public List<Map<String, String>> getServiceRequestCategory() {
		return ServiceRequestCategory;
	}
	public void setServiceRequestCategory(List<Map<String, String>> serviceRequestCategory) {
		ServiceRequestCategory = serviceRequestCategory;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@javax.persistence.Transient
	private String message;
}
