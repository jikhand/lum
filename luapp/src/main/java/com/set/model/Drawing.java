package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecate")
@Entity
@Table(name = "lutbl_drawing")
public class Drawing {

	@Id
	@Column(name = "drawing_id", columnDefinition = "VARCHAR(16)")
	private String drawingId;
	@Column(name = "student_id", columnDefinition = "VARCHAR(16)")
	private String studentId;
	@Column(name = "class_id", columnDefinition = "VARCHAR(16)")
	private String classId;
	@Column(name = "section_id", columnDefinition = "VARCHAR(16)")
	private String sectionId;
	@Column(name = "drawing_name", columnDefinition = "VARCHAR(16)")
	private String drawingName;
	@Column(name = "drawing_category", columnDefinition = "VARCHAR(16)")
	private String drawingCategory;
	@Column(name = "drawingType", columnDefinition = "VARCHAR(16)")
	private String drawingType;
	@Column(name = "drawingCode", columnDefinition = "VARCHAR(16)")
	private String drawingCode;
	@Column(name = "image_url", columnDefinition = "VARCHAR(255)")
	private String imageUrl;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Transient
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
}