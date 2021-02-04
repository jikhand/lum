package com.set.model;

import java.io.Serializable;
import java.util.List;

public class StudentNotesDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<StudentNotes> studentNotes;
	public List<StudentNotes> getStudentNotes() {
		return studentNotes;
	}
	public void setStudentNotes(List<StudentNotes> studentNotes) {
		this.studentNotes = studentNotes;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
