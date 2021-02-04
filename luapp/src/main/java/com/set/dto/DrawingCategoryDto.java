package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrawingCategoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8018278697322448478L;
	private String DrawingId;
	private String DrawingName;
	private String ImageUrl;
	private String DrawingCategoryName;
	private String ClassId;
	private String SectionId;
	private String StudentId;
	
	
	
	public String getDrawingId() {
		return DrawingId;
	}

	@JsonProperty("DrawingId")
	public void setDrawingId(String drawingId) {
		DrawingId = drawingId;
	}

	public String getDrawingName() {
		return DrawingName;
	}

	@JsonProperty("DrawingName")
	public void setDrawingName(String drawingName) {
		DrawingName = drawingName;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	@JsonProperty("ImageUrl")
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getDrawingCategoryName() {
		return DrawingCategoryName;
	}

	@JsonProperty("DrawingCategoryName")
	public void setDrawingCategoryName(String drawingCategoryName) {
		DrawingCategoryName = drawingCategoryName;
	}

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getSectionId() {
		return SectionId;
	}

	@JsonProperty("SectionId")
	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
