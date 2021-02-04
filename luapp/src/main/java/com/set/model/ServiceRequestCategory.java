package com.set.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_srcategory")
public class ServiceRequestCategory {

	@Id
	@Column(name = "category_id", columnDefinition = "VARCHAR(16)")
	private String categoryId;
	@Column(name = "assigned_to", columnDefinition = "VARCHAR(16)")
	private String assignedTo;
	@Column(name = "description", columnDefinition = "VARCHAR(45)")
	private String description;
	@Column(name = "resolve_days", columnDefinition = "int(2)")
	private int resolveDays;
	@Column(name = "category_type", columnDefinition = "VARCHAR(1)")
	private String categoryType;
	@Column(name = "parent_category", columnDefinition = "VARCHAR(45)")
	private String parentCategory;
	@Column(name = "is_soft_delete", columnDefinition = "int(1)")
	private int isSoftDelete;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;

    @Transient
    private String message;
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getResolveDays() {
		return resolveDays;
	}

	public void setResolveDays(int resolveDays) {
		this.resolveDays = resolveDays;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getIsSoftDelete() {
		return isSoftDelete;
	}

	public void setIsSoftDelete(int isSoftDelete) {
		this.isSoftDelete = isSoftDelete;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	
}
