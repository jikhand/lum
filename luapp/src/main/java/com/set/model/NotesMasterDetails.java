package com.set.model;

import java.io.Serializable;
import java.util.List;

public class NotesMasterDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<NotesMaster> notesMaster;
	public List<NotesMaster> getNotesMaster() {
		return notesMaster;
	}
	public void setNotesMaster(List<NotesMaster> notesMaster) {
		this.notesMaster = notesMaster;
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
