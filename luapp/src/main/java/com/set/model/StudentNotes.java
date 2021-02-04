package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "lutbl_student_notes")
public class StudentNotes {

	@Id
	@EmbeddedId
	private StudentNotesId StudentNotesId;
	@Column(name = "notes_title", columnDefinition = "VARCHAR(45)")
	private String NotesTitle;
	@Column(name = "notes_descript", columnDefinition = "VARCHAR(80)")
	private String NotesDescription;
	@Column(name = "page_path", columnDefinition = "VARCHAR(240)")
	private String PagePath;
	@Column(name = "status", columnDefinition = "CHAR(1)")
	private char Status;
	@Column(name = "page_creat_date")
	private Date PageCreateDate;
	@Column(name = "for_use_field1", columnDefinition = "VARCHAR(45)")
	private String forUseField1;
	@Column(name = "for_use_field2", columnDefinition = "VARCHAR(45)")
	private String forUseField2;
	@Column(name = "for_use_field3", columnDefinition = "VARCHAR(45)")
	private String forUseField3;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_time")
	private Date updatedTime;
	@Transient
	private String message;
	@Transient
	private String NotesId;
	@Transient
	private String StudentId;
	@Transient
	private Page[] Page;

	public String getNotesId() {
		return NotesId;
	}

	@JsonProperty("NotesId")
	public void setNotesId(String notesId) {
		NotesId = notesId;
	}

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public Page[] getPage() {
		return Page;
	}

	@JsonProperty("Page")
	public void setPage(Page[] page) {
		this.Page = page;
	}

	public String getMessage() {
		return message;
	}

	@JsonProperty("CoverPhoto")
	public void setMessage(String message) {
		this.message = message;
	}

	public StudentNotesId getStudentNotesId() {
		return StudentNotesId;
	}

	@JsonProperty("StudentNotesId")
	public void setStudentNotesId(StudentNotesId studentNotesId) {
		this.StudentNotesId = studentNotesId;
	}

	public String getNotesTitle() {
		return NotesTitle;
	}

	@JsonProperty("NotesTitle")
	public void setNotesTitle(String notesTitle) {
		this.NotesTitle = notesTitle;
	}

	public String getNotesDescription() {
		return NotesDescription;
	}

	@JsonProperty("NotesDescription")
	public void setNotesDescription(String notesDescription) {
		this.NotesDescription = notesDescription;
	}

	public String getPagePath() {
		return PagePath;
	}

	@JsonProperty("PagePath")
	public void setPagePath(String pagePath) {
		this.PagePath = pagePath;
	}

	public char getStatus() {
		return Status;
	}

	@JsonProperty("Status")
	public void setStatus(char status) {
		this.Status = status;
	}

	public Date getPageCreateDate() {
		return PageCreateDate;
	}

	@JsonProperty("PageCreateDate")
	public void setPageCreateDate(Date pageCreateDate) {
		this.PageCreateDate = pageCreateDate;
	}

	public String getForUseField1() {
		return forUseField1;
	}

	public void setForUseField1(String forUseField1) {
		this.forUseField1 = forUseField1;
	}

	public String getForUseField2() {
		return forUseField2;
	}

	public void setForUseField2(String forUseField2) {
		this.forUseField2 = forUseField2;
	}

	public String getForUseField3() {
		return forUseField3;
	}

	public void setForUseField3(String forUseField3) {
		this.forUseField3 = forUseField3;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
