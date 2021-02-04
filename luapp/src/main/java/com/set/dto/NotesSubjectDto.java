package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotesSubjectDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -813048385504086284L;
	private String SubjectId;
	private String SubjectName;
	private List<NotesUnitDto> NotesUnitList;
	

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public List<NotesUnitDto> getNotesUnitList() {
		return NotesUnitList;
	}

	@JsonProperty("unitdata")
	public void setNotesUnitList(List<NotesUnitDto> notesUnitList) {
		NotesUnitList = notesUnitList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
