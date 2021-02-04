package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotesDetailsDto {
	private List<StudentNotesDto> StudentNotesList;
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
	public List<StudentNotesDto> getStudentNotesDtoList() {
		return StudentNotesList;
	}
	@JsonProperty("StudentNotesList")
	public void setStudentNotesDtoList(List<StudentNotesDto> studentNotesDtoList) {
		this.StudentNotesList = studentNotesDtoList;
	}
}
