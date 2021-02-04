package com.set.model;

import java.io.Serializable;
import java.util.List;

public class DrawingCategoryDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<DrawingCategory> drawingCategory;
	public List<DrawingCategory> getDrawingCategory() {
		return drawingCategory;
	}
	public void setDrawingCategory(List<DrawingCategory> drawingCategory) {
		this.drawingCategory = drawingCategory;
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
