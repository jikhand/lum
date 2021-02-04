package com.set.model;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = -781484081017119318L;

	public String NotesId;
	public int PageNo;
	public String StudentId;
	public String PagePath;

	public int getPageNo() {
		return PageNo;
	}

	public void setPageNo(int pageNo) {
		this.PageNo = pageNo;
	}

	public String getPagePath() {
		return PagePath;
	}

	public void setPagePath(String pagePath) {
		this.PagePath = pagePath;
	}

	public String getNotesId() {
		return NotesId;
	}

	public void setNotesId(String notesId) {
		this.NotesId = notesId;
	}

	public String getStudentId() {
		return StudentId;
	}

	public void setStudentId(String studentId) {
		this.StudentId = studentId;
	}

	@Override
	public String toString() {
		return getPageNo() + ", " + getPagePath() + ", " + getNotesId() + ", " + getStudentId();
	}
}
