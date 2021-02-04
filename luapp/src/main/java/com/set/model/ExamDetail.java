package com.set.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamDetail {

	public int NoQuestion;
	public String QuestionType;
		
	public int getNoQuestion() {
		return NoQuestion;
	}

	@JsonProperty("NoQuestion")
	public void setNoQuestion(int noQuestion) {
		NoQuestion = noQuestion;
	}

	public String getQuestionType() {
		return QuestionType;
	}

	@JsonProperty("QuestionType")
	public void setQuestionType(String questionType) {
		QuestionType = questionType;
	}

	@Override
	public String toString(){
		return getNoQuestion() + ", "+getQuestionType();
	}

	public void setIsDeleted(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
