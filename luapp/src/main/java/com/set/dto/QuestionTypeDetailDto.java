package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionTypeDetailDto {
	private List<QuestionDetailsDto> QuestionDetailsList;
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
	public List<QuestionDetailsDto> getQuestionDetailsDtoList() {
		return QuestionDetailsList;
	}
	@JsonProperty("TestData")
	public void setQuestionDetailsDtoList(List<QuestionDetailsDto> questionDetailsDtoList) {
		this.QuestionDetailsList = questionDetailsDtoList;
	}
}
