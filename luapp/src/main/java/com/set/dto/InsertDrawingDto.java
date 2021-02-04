package com.set.dto;

import java.io.Serializable;
import java.util.Date;

public class InsertDrawingDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6898095594896742867L;
	private String drawingId;
	private String studentId;
	private String classId;
	private String sectionId;
	private String drawingName;
	private String drawingCategory;
	private String drawingCategoryName;
	private String drawingType;
	private String drawingCode;
	private String imageUrl;
	private Date createdAt;
	private Date updatedAt;
	private boolean isDeleted;
	private String message;

	public String getDrawingId() {
		return drawingId;
	}

	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getDrawingName() {
		return drawingName;
	}

	public void setDrawingName(String drawingName) {
		this.drawingName = drawingName;
	}

	public String getDrawingCategory() {
		return drawingCategory;
	}

	public void setDrawingCategory(String drawingCategory) {
		this.drawingCategory = drawingCategory;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDrawingType() {
		return drawingType;
	}

	public void setDrawingType(String drawingType) {
		this.drawingType = drawingType;
	}

	public String getDrawingCode() {
		return drawingCode;
	}

	public void setDrawingCode(String drawingCode) {
		this.drawingCode = drawingCode;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDrawingCategoryName() {
		return drawingCategoryName;
	}

	public void setDrawingCategoryName(String drawingCategoryName) {
		this.drawingCategoryName = drawingCategoryName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}