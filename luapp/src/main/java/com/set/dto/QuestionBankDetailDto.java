package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionBankDetailDto {
	private List<QuestionBankDto> QuestionList;
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
	public List<QuestionBankDto> getQuestionList() {
		return QuestionList;
	}
	@JsonProperty("QuestionBankData")
	public void setQuestionList(List<QuestionBankDto> questionList) {
		QuestionList = questionList;
	}
	
}
