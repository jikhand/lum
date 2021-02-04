package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmitStudentDetailsDto {
	private List<SubmitStudentDto> SubmitStudentList;
	private List<AbsentStudentDto> AbsentStudentList;
	private int SubmitStudentsCount;
	private int AbsentStudentsCount;
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
	public int getSubmitStudentsCount() {
		return SubmitStudentsCount;
	}
	@JsonProperty("SubmitStudentsCount")
	public void setSubmitStudentsCount(int submitStudentsCount) {
		SubmitStudentsCount = submitStudentsCount;
	}
	public int getAbsentStudentsCount() {
		return AbsentStudentsCount;
	}
	@JsonProperty("AbsentStudentsCount")
	public void setAbsentStudentsCount(int absentStudentsCount) {
		AbsentStudentsCount = absentStudentsCount;
	}
	public List<SubmitStudentDto> getSubmitStudentDtoList() {
		return SubmitStudentList;
	}
	@JsonProperty("SubmitStudentList")
	public void setSubmitStudentDtoList(List<SubmitStudentDto> submitStudentDtoList) {
		this.SubmitStudentList = submitStudentDtoList;
	}
	public List<AbsentStudentDto> getAbsentStudentList() {
		return AbsentStudentList;
	}
	@JsonProperty("AbsentStudentList")
	public void setAbsentStudentList(List<AbsentStudentDto> absentStudentList) {
		AbsentStudentList = absentStudentList;
	}
}
