package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrawingDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -216469185346370029L;
	private String DrawingCategoryName;
	private String DrawingId;
	private String DrawingCategoryId;
	private String DrawingName;
	private String Drawing;
	
	public String getDrawingCategoryName() {
		return DrawingCategoryName;
	}

	@JsonProperty("DrawingCategoryName")
	public void setDrawingCategoryName(String drawingCategoryName) {
		DrawingCategoryName = drawingCategoryName;
	}

	public String getDrawingId() {
		return DrawingId;
	}

	@JsonProperty("DrawingId")
	public void setDrawingId(String drawingId) {
		DrawingId = drawingId;
	}

	public String getDrawingCategoryId() {
		return DrawingCategoryId;
	}

	@JsonProperty("DrawingCategoryId")
	public void setDrawingCategoryId(String drawingCategoryId) {
		DrawingCategoryId = drawingCategoryId;
	}

	public String getDrawingName() {
		return DrawingName;
	}

	@JsonProperty("DrawingName")
	public void setDrawingName(String drawingName) {
		DrawingName = drawingName;
	}

	public String getDrawing() {
		return Drawing;
	}

	@JsonProperty("Drawing")
	public void setDrawing(String drawing) {
		Drawing = drawing;
	}

}
