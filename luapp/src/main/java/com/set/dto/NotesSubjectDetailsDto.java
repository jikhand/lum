package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotesSubjectDetailsDto {
	private List<NotesSubjectDto> NotesSubjectClassList;
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
	public List<NotesSubjectDto> getNotesSubjectClassDtoList() {
		return NotesSubjectClassList;
	}
	@JsonProperty("Notes")
	public void setNotesSubjectClassDtoList(List<NotesSubjectDto> notesSubjectClassDtoList) {
		this.NotesSubjectClassList = notesSubjectClassDtoList;
	}
}
