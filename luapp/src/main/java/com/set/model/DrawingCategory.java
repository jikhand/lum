package com.set.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecate")
@Entity
@Table(name="lutbl_drawing_category")
public class DrawingCategory {
	
	@Id
	@Column(name="drawing_category_id",columnDefinition="VARCHAR(16)")
	private String drawingCategoryId;
	@Column(name="drawing_category_name",columnDefinition="VARCHAR(255)")
	private String drawingCategoryName;
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="updated_at")
	private Date updatedAt;
	@Column(name="is_deleted")
	private boolean isDeleted;
	@Transient
	private String message;
	
	public String getDrawingCategoryId() {
		return drawingCategoryId;
	}
	public void setDrawingCategoryId(String drawingCategoryId) {
		this.drawingCategoryId = drawingCategoryId;
	}
	public String getDrawingCategoryName() {
		return drawingCategoryName;
	}
	public void setDrawingCategoryName(String drawingCategoryName) {
		this.drawingCategoryName = drawingCategoryName;
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
}