package com.set.model;

import java.io.Serializable;
import java.util.List;

public class QuestionTypeDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<QuestionType> questionType;
	public List<QuestionType> getQuestionType() {
		return questionType;
	}
	public void setQuestionType(List<QuestionType> questionType) {
		this.questionType = questionType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
