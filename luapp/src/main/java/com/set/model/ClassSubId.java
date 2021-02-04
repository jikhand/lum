package com.set.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassSubId{
	/**
	 * 
	 */
	private String ClassId;
	private String SubjectId;
	private String QuestionTypeId;
	private String QuestionDate;
	
	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getQuestionTypeId() {
		return QuestionTypeId;
	}

	@JsonProperty("QuestionTypeId")
	public void setQuestionTypeId(String questionTypeId) {
		QuestionTypeId = questionTypeId;
	}

	public String getQuestionDate() {
		return QuestionDate;
	}

	@JsonProperty("QuestionDate")
	public void setQuestionDate(String questionDate) {
		QuestionDate = questionDate;
	}
	
	
}
