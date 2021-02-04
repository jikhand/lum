package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionBankDto implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -1187026349195582021L;
	private String QuestionId;
	private String Question;
	private String QuestionType;
	private String QuestionPath;
	private String Optiona;
	private String Optionb;
	private String Optionc;
	private String Optiond;
	private String LeftSideOptiona;
	private String LeftSideOptionb;
	private String LeftSideOptionc;
	private String LeftSideOptiond;
	private String LeftSideOptione;
	private String RightSideOption1;
	private String RightSideOption2;
	private String RightSideOption3;
	private String RightSideOption4;
	private String RightSideOption5;
	
	public String getQuestionId() {
		return QuestionId;
	}
	@JsonProperty("QuestionId")
	public void setQuestionId(String questionId) {
		QuestionId = questionId;
	}
	public String getQuestion() {
		return Question;
	}
	@JsonProperty("Question")
	public void setQuestion(String question) {
		Question = question;
	}
	public String getQuestionType() {
		return QuestionType;
	}
	@JsonProperty("QuestionType")
	public void setQuestionType(String questionType) {
		QuestionType = questionType;
	}
	public String getQuestionPath() {
		return QuestionPath;
	}
	@JsonProperty("QuestionPath")
	public void setQuestionPath(String questionPath) {
		QuestionPath = questionPath;
	}
	public String getOptiona() {
		return Optiona;
	}
	@JsonProperty("Optiona")
	public void setOptiona(String optiona) {
		Optiona = optiona;
	}
	public String getOptionb() {
		return Optionb;
	}
	@JsonProperty("Optionb")
	public void setOptionb(String optionb) {
		Optionb = optionb;
	}
	public String getOptionc() {
		return Optionc;
	}
	@JsonProperty("Optionc")
	public void setOptionc(String optionc) {
		Optionc = optionc;
	}
	public String getOptiond() {
		return Optiond;
	}
	@JsonProperty("Optiond")
	public void setOptiond(String optiond) {
		Optiond = optiond;
	}
	public String getLeftSideOptiona() {
		return LeftSideOptiona;
	}
	@JsonProperty("LeftSideOptiona")
	public void setLeftSideOptiona(String leftSideOptiona) {
		LeftSideOptiona = leftSideOptiona;
	}
	public String getLeftSideOptionb() {
		return LeftSideOptionb;
	}
	@JsonProperty("LeftSideOptionb")
	public void setLeftSideOptionb(String leftSideOptionb) {
		LeftSideOptionb = leftSideOptionb;
	}
	public String getLeftSideOptionc() {
		return LeftSideOptionc;
	}
	@JsonProperty("LeftSideOptionc")
	public void setLeftSideOptionc(String leftSideOptionc) {
		LeftSideOptionc = leftSideOptionc;
	}
	public String getLeftSideOptiond() {
		return LeftSideOptiond;
	}
	@JsonProperty("LeftSideOptiond")
	public void setLeftSideOptiond(String leftSideOptiond) {
		LeftSideOptiond = leftSideOptiond;
	}
	public String getLeftSideOptione() {
		return LeftSideOptione;
	}
	@JsonProperty("LeftSideOptione")
	public void setLeftSideOptione(String leftSideOptione) {
		LeftSideOptione = leftSideOptione;
	}
	public String getRightSideOption1() {
		return RightSideOption1;
	}
	@JsonProperty("RightSideOption1")
	public void setRightSideOption1(String rightSideOption1) {
		RightSideOption1 = rightSideOption1;
	}
	public String getRightSideOption2() {
		return RightSideOption2;
	}
	@JsonProperty("RightSideOption2")
	public void setRightSideOption2(String rightSideOption2) {
		RightSideOption2 = rightSideOption2;
	}
	public String getRightSideOption3() {
		return RightSideOption3;
	}
	@JsonProperty("RightSideOption3")
	public void setRightSideOption3(String rightSideOption3) {
		RightSideOption3 = rightSideOption3;
	}
	public String getRightSideOption4() {
		return RightSideOption4;
	}
	@JsonProperty("RightSideOption4")
	public void setRightSideOption4(String rightSideOption4) {
		RightSideOption4 = rightSideOption4;
	}
	public String getRightSideOption5() {
		return RightSideOption5;
	}
	@JsonProperty("RightSideOption5")
	public void setRightSideOption5(String rightSideOption5) {
		RightSideOption5 = rightSideOption5;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
