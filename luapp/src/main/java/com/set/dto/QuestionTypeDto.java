package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionTypeDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2620274637826362183L;
	private String QuestionType;
	private String Description;
		
	public String getQuestionType() {
		return QuestionType;
	}

	public void setQuestionType(String questionType) {
		QuestionType = questionType;
	}

	@JsonProperty("QuestionType")
	public String getDescription() {
		return Description;
	}

	@JsonProperty("Description")
	public void setDescription(String description) {
		Description = description;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
