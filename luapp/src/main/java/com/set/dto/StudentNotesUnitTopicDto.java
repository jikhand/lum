package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class StudentNotesUnitTopicDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912762751339845142L;
	private String SubjectNotesCategoryId;
	private String UnitName;
	private String TeacherId;
	private String CategoryThumbinal;
	private String PageTypeName;
	private String TopicName;
	private List<StudentNotesDto> StudentNotesList;

	public String getSubjectNotesCategoryId() {
		return SubjectNotesCategoryId;
	}

	@JsonProperty("SubjectNotesCategoryId")
	public void setSubjectNotesCategoryId(String subjectNotesCategoryId) {
		SubjectNotesCategoryId = subjectNotesCategoryId;
	}

	public String getUnitName() {
		return UnitName;
	}

	@JsonProperty("UnitName")
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}

	public String getTeacherId() {
		return TeacherId;
	}

	@JsonProperty("TeacherId")
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

	public String getCategoryThumbinal() {
		return CategoryThumbinal;
	}

	@JsonProperty("CategoryThumbinal")
	public void setCategoryThumbinal(String categoryThumbinal) {
		CategoryThumbinal = categoryThumbinal;
	}

	public String getPageTypeName() {
		return PageTypeName;
	}

	@JsonProperty("PageTypeName")
	public void setPageTypeName(String pageTypeName) {
		PageTypeName = pageTypeName;
	}

	public String getTopicName() {
		return TopicName;
	}

	@JsonProperty("TopicName")
	public void setTopicName(String topicName) {
		TopicName = topicName;
	}

	public List<StudentNotesDto> getStudentNotesList() {
		return StudentNotesList;
	}

	@JsonProperty("pagedata")
	public void setStudentNotesList(List<StudentNotesDto> studentNotesList) {
		StudentNotesList = studentNotesList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
