package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class DrawingCategoryNameDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7913894947061792137L;
	private List<DrawingCategoryDto> DrawingCategoryList;
	private String DrawingCategoryId;
	private String DrawingCategoryName;
	

	public String getDrawingCategoryId() {
		return DrawingCategoryId;
	}

	@JsonProperty("DrawingCategoryId")
	public void setDrawingCategoryId(String drawingCategoryId) {
		DrawingCategoryId = drawingCategoryId;
	}

	public String getDrawingCategoryName() {
		return DrawingCategoryName;
	}

	@JsonProperty("DrawingCategoryName")
	public void setDrawingCategoryName(String drawingCategoryName) {
		DrawingCategoryName = drawingCategoryName;
	}


	public List<DrawingCategoryDto> getDrawingCategoryList() {
		return DrawingCategoryList;
	}

	@JsonProperty("drawings")
	public void setDrawingCategoryList(List<DrawingCategoryDto> drawingCategoryList) {
		DrawingCategoryList = drawingCategoryList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
