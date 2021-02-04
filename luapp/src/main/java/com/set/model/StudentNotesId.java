package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class StudentNotesId implements Serializable {
	private static final long serialVersionUID = 5630049354899746407L;
	@Column(name = "notes_id", columnDefinition = "VARCHAR(16)")
	private String NotesId;
	@Column(name = "page_no", columnDefinition = "int(11)")
	private int PageNo;
	@Column(name = "student_id", columnDefinition = "VARCHAR(16)")
	private String StudentId;

	public StudentNotesId() {
	}

	public StudentNotesId(String notesId, int pageNo, String studentId) {
		this.NotesId = notesId;
		this.PageNo = pageNo;
		this.StudentId = studentId;
	}

	public String getNotesId() {
		return NotesId;
	}

	@JsonProperty("NotesId")
	public void setNotesId(String notesId) {
		this.NotesId = notesId;
	}

	public int getPageNo() {
		return PageNo;
	}

	@JsonProperty("PageNo")
	public void setPageNo(int pageNo) {
		this.PageNo = pageNo;
	}

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		this.StudentId = studentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof StudentNotesId))
			return false;
		StudentNotesId that = (StudentNotesId) o;
		return Objects.equals(getNotesId(), that.getNotesId()) && Objects.equals(getPageNo(), that.getPageNo())
				&& Objects.equals(getStudentId(), that.getStudentId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNotesId(), getPageNo(), getStudentId());
	}
}
