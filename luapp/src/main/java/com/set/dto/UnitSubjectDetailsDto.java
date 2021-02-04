package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitSubjectDetailsDto {
	private List<UnitSubjectClassDto> UnitSubjectClassList;
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
	public List<UnitSubjectClassDto> getUnitSubjectClassDtoList() {
		return UnitSubjectClassList;
	}
	@JsonProperty("classdata")
	public void setUnitSubjectClassDtoList(List<UnitSubjectClassDto> unitSubjectClassDtoList) {
		this.UnitSubjectClassList = unitSubjectClassDtoList;
	}
}
