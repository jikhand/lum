package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class QuestionDetailsDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2217491833765302860L;
	private List<QuestionDto> QuestionList;
	private String QuestionTypeName;
	private String QuestionTypeId;
	private String PartName;
	private String Marks;
	
	public List<QuestionDto> getQuestionList() {
		return QuestionList;
	}
	@JsonProperty("QuestionList")
	public void setQuestionList(List<QuestionDto> questionList) {
		QuestionList = questionList;
	}
	public String getQuestionTypeName() {
		return QuestionTypeName;
	}
	@JsonProperty("QuestionTypeName")
	public void setQuestionTypeName(String questionTypeName) {
		QuestionTypeName = questionTypeName;
	}
	public String getQuestionTypeId() {
		return QuestionTypeId;
	}
	@JsonProperty("QuestionTypeId")
	public void setQuestionTypeId(String questionTypeId) {
		QuestionTypeId = questionTypeId;
	}
	public String getPartName() {
		return PartName;
	}
	@JsonProperty("PartName")
	public void setPartName(String partName) {
		PartName = partName;
	}
	public String getMarks() {
		return Marks;
	}
	@JsonProperty("Marks")
	public void setMarks(String marks) {
		Marks = marks;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
