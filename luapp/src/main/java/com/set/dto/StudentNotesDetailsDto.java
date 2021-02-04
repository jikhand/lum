package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentNotesDetailsDto {
	private List<StudentNotesSubjectDto> StudentNotesSubjectList;
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
	public List<StudentNotesSubjectDto> getStudentNotesSubjectList() {
		return StudentNotesSubjectList;
	}
	@JsonProperty("Notes")
	public void setStudentNotesSubjectList(List<StudentNotesSubjectDto> studentNotesSubjectList) {
		StudentNotesSubjectList = studentNotesSubjectList;
	}
}
