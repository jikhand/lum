package com.set.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "lutbl_notes_master")
public class NotesMaster {
	@Id
	@Column(name = "notes_id", columnDefinition = "VARCHAR(16)")
	private String NotesId;
	@Column(name = "subj_id", columnDefinition = "VARCHAR(16)")
	private String SubjectId;
	@Column(name = "unit_id", columnDefinition = "VARCHAR(16)")
	private String UnitId;
	@Column(name = "topic_id", columnDefinition = "VARCHAR(16)")
	private String TopicId;
	@Column(name = "teacher_id", columnDefinition = "VARCHAR(16)")
	private String TeacherId;
	@Column(name = "page_type", columnDefinition = "VARCHAR(45)")
	private String PageType;
	@Column(name = "cover_photo", columnDefinition = "VARCHAR(255)")
	private String CoverPhoto;
	@Transient
	private String message;

	public String getNotesId() {
		return NotesId;
	}
	@JsonProperty("NotesId")
	public void setNotesId(String notesId) {
		this.NotesId = notesId;
	}

	public String getSubjectId() {
		return SubjectId;
	}
	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		this.SubjectId = subjectId;
	}

	public String getUnitId() {
		return UnitId;
	}
	@JsonProperty("UnitId")
	public void setUnitId(String unitId) {
		this.UnitId = unitId;
	}

	public String getTopicId() {
		return TopicId;
	}
	@JsonProperty("TopicId")
	public void setTopicId(String topicId) {
		this.TopicId = topicId;
	}

	public String getTeacherId() {
		return TeacherId;
	}
	@JsonProperty("TeacherId")
	public void setTeacherId(String teacherId) {
		this.TeacherId = teacherId;
	}

	public String getPageType() {
		return PageType;
	}
	@JsonProperty("PageType")
	public void setPageType(String pageType) {
		this.PageType = pageType;
	}

	public String getCoverPhoto() {
		return CoverPhoto;
	}
	@JsonProperty("CoverPhoto")
	public void setCoverPhoto(String coverPhoto) {
		this.CoverPhoto = coverPhoto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
