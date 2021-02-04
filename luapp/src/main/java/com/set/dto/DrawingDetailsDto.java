package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrawingDetailsDto {
	private List<DrawingDto> DrawingList;
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
	public List<DrawingDto> getDrawingDtoList() {
		return DrawingList;
	}
	@JsonProperty("DrawingList")
	public void setDrawingDtoList(List<DrawingDto> drawingDtoList) {
		this.DrawingList = drawingDtoList;
	}
}
