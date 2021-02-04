package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceBankDetailsDto {
	private List<ResourceBankSubjectDto> ResourceBankSubjectList;
	private int count;
	@javax.persistence.Transient
	private String message;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ResourceBankSubjectDto> getResourceBankSubjectList() {
		return ResourceBankSubjectList;
	}
	@JsonProperty("ResourceBank")
	public void setResourceBankSubjectList(List<ResourceBankSubjectDto> resourceBankSubjectList) {
		ResourceBankSubjectList = resourceBankSubjectList;
	}
}
