package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentNotesSubjectDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2755187695514615920L;
	private String SubjectId;
	private String SubjectName;
	private List<StudentNotesUnitTopicDto> StudentNotesUnitTopicList;
	

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

	public List<StudentNotesUnitTopicDto> getStudentNotesUnitTopicList() {
		return StudentNotesUnitTopicList;
	}

	@JsonProperty("units")
	public void setStudentNotesUnitTopicList(List<StudentNotesUnitTopicDto> studentNotesUnitTopicList) {
		StudentNotesUnitTopicList = studentNotesUnitTopicList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
