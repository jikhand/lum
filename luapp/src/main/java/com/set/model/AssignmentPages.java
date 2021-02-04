package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "lutbl_assgn_pages")
public class AssignmentPages {
	@EmbeddedId
	private AssignmentPageId AssignmentPageId;
	@Column(name = "page_path", columnDefinition = "VARCHAR(255)")
	private String PagePath;
	@Column(name = "page_type", columnDefinition = "VARCHAR(45)")
	private String PageType;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String InsertedBy;
	@Column(name = "inserted_time")
	private Date InsertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String UpdatedBy;
	@Column(name = "updated_time")
	private Date UpdatedTime;
	@Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
	private int IsDeleted = 0;

	public AssignmentPageId getAssignmentPageId() {
		return AssignmentPageId;
	}

	@JsonProperty("AssignmentPageId")
	public void setAssignmentPageId(AssignmentPageId assignmentPageId) {
		AssignmentPageId = assignmentPageId;
	}

	public String getPagePath() {
		return PagePath;
	}

	@JsonProperty("PagePath")
	public void setPagePath(String pagePath) {
		PagePath = pagePath;
	}

	public String getPageType() {
		return PageType;
	}

	@JsonProperty("PageType")
	public void setPageType(String pageType) {
		PageType = pageType;
	}

	public String getInsertedBy() {
		return InsertedBy;
	}

	@JsonProperty("InsertedBy")
	public void setInsertedBy(String insertedBy) {
		InsertedBy = insertedBy;
	}

	public Date getInsertedTime() {
		return InsertedTime;
	}

	@JsonProperty("InsertedTime")
	public void setInsertedTime(Date insertedTime) {
		InsertedTime = insertedTime;
	}

	public String getUpdatedBy() {
		return UpdatedBy;
	}

	@JsonProperty("UpdatedBy")
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return UpdatedTime;
	}

	@JsonProperty("UpdatedTime")
	public void setUpdatedTime(Date updatedTime) {
		UpdatedTime = updatedTime;
	}

	public int getIsDeleted() {
		return IsDeleted;
	}

	@JsonProperty("IsDeleted")
	public void setIsDeleted(int isDeleted) {
		IsDeleted = isDeleted;
	}
	
}
