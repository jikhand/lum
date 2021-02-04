package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotesStudentId{
	/**
	 * 
	 */
	private String StudentId;
	private String NotesId;

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public String getNotesId() {
		return NotesId;
	}

	@JsonProperty("NotesId")
	public void setNotesId(String notesId) {
		NotesId = notesId;
	}
	
	
}
