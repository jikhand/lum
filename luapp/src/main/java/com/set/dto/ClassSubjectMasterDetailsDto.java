package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassSubjectMasterDetailsDto {
	private List<ClassSubjectDetailsDto> ClassSubjects;
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
	public List<ClassSubjectDetailsDto> getClassSubjectsDtoList() {
		return ClassSubjects;
	}
	@JsonProperty("ClassSubjects")
	public void setClassSubjectsDtoList(List<ClassSubjectDetailsDto> classSubjectsDtoList) {
		this.ClassSubjects = classSubjectsDtoList;
	}
}
