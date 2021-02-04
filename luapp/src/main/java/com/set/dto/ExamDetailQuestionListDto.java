package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExamDetailQuestionListDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6449548600791849527L;
	private String NoOfQuestions;
	private String QuestionTypeId;
	
	public String getNoOfQuestions() {
		return NoOfQuestions;
	}
	@JsonProperty("NoOfQuestions")
	public void setNoOfQuestions(String noOfQuestions) {
		NoOfQuestions = noOfQuestions;
	}
	public String getQuestionTypeId() {
		return QuestionTypeId;
	}
	@JsonProperty("QuestionTypeId")
	public void setQuestionTypeId(String questionTypeId) {
		QuestionTypeId = questionTypeId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
