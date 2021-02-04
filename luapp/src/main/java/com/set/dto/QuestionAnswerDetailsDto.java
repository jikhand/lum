package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class QuestionAnswerDetailsDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8513887295140667406L;
	private List<QuestionAnswerDto> QuestionAnswerList;
	private String QuestionAnswerTypeName;
	private String QuestionAnswerTypeId;
	private String PartName;
	private String Marks;
	
	public List<QuestionAnswerDto> getQuestionAnswerList() {
		return QuestionAnswerList;
	}
	@JsonProperty("QuestionAnswerList")
	public void setQuestionAnswerList(List<QuestionAnswerDto> questionAnswerList) {
		QuestionAnswerList = questionAnswerList;
	}
	public String getQuestionAnswerTypeName() {
		return QuestionAnswerTypeName;
	}
	@JsonProperty("QuestionAnswerTypeName")
	public void setQuestionAnswerTypeName(String questionAnswerTypeName) {
		QuestionAnswerTypeName = questionAnswerTypeName;
	}
	public String getQuestionAnswerTypeId() {
		return QuestionAnswerTypeId;
	}
	@JsonProperty("QuestionAnswerTypeId")
	public void setQuestionAnswerTypeId(String questionAnswerTypeId) {
		QuestionAnswerTypeId = questionAnswerTypeId;
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
