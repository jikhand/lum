package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamTypeDetailsDto {
	private List<ExamTypeDto> ExamTypeList;
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
	public List<ExamTypeDto> getExamTypeList() {
		return ExamTypeList;
	}
	@JsonProperty("ExamTypeList")
	public void setExamTypeList(List<ExamTypeDto> examTypeList) {
		ExamTypeList = examTypeList;
	}
}
