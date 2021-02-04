package com.set.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
public class GetServiceRequestDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843791L;
	private Map<String, String> ServiceRequest;
	
	public Map<String, String> getServiceRequest() {
		return ServiceRequest;
	}
	public void setServiceRequest(Map<String, String> hm) {
		ServiceRequest = hm;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@javax.persistence.Transient
	private String message;
}
