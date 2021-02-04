package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionAnswerTypeDetailDto {
	private List<QuestionAnswerDetailsDto> QuestionDetailsList;
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
	public List<QuestionAnswerDetailsDto> getQuestionAnswerDetailsDtoList() {
		return QuestionDetailsList;
	}
	@JsonProperty("TestData")
	public void setQuestionAnswerDetailsDtoList(List<QuestionAnswerDetailsDto> questionAnswerDetailsDtoList) {
		this.QuestionDetailsList = questionAnswerDetailsDtoList;
	}
}
