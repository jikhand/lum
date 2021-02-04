package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrawingCategoryDetailsDto {
	private List<DrawingCategoryNameDto> DrawingCategoryNameList;
	private int count;
	@javax.persistence.Transient
	private String message;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<DrawingCategoryNameDto> getDrawingCategoryNameDtoList() {
		return DrawingCategoryNameList;
	}
	@JsonProperty("Drawings")
	public void setDrawingCategoryNameDtoList(List<DrawingCategoryNameDto> drawingCategoryNameDtoList) {
		this.DrawingCategoryNameList = drawingCategoryNameDtoList;
	}
}
