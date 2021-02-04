package com.set.model;

import java.io.Serializable;
import java.util.List;

public class DrawingDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<Drawing> drawing;
	public List<Drawing> getDrawing() {
		return drawing;
	}
	public void setDrawing(List<Drawing> drawing) {
		this.drawing = drawing;
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
