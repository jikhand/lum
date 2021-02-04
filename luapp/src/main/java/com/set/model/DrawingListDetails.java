package com.set.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DrawingListDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2006296879156289660L;
	private List<Map<String, String>> DrawingList;
	private int count;
	
	public List<Map<String, String>> getDrawingList() {
		return DrawingList;
	}
	public void setDrawingList(List<Map<String, String>> drawingList) {
		DrawingList = drawingList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
