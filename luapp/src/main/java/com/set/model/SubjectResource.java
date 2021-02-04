package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_subject_resource")
public class SubjectResource {
	@EmbeddedId
	private SubjectResourceId SubjectResourceId;
	@Column(name="resource_type",columnDefinition="VARCHAR(5)")
	private String ResourceType;
	@Column(name="thumbnail_image",columnDefinition="VARCHAR(240)")
	private String ThumbnailImage;
	@Column(name="resource_path",columnDefinition="VARCHAR(240)")
	private String ResourcePath;
	@Column(name="resource_link",columnDefinition="VARCHAR(240)",nullable = true)
	private String ResourceLink;
	@Column(name="resource_title",columnDefinition="VARCHAR(45)")
	private String ResourceTitle;
	@Column(name="resource_extension",columnDefinition="VARCHAR(45)")
	private String ResourceExtension;
	@Column(name="is_soft_delete",columnDefinition="int(1)")
	private int is_soft_delete;
	@Column(name="inserted_by",columnDefinition="VARCHAR(45)")
	private String insertedBy;
	@Column(name="inserted_time")
	private Date insertedTime;
	@Column(name="updated_by",columnDefinition="VARCHAR(45)")
	private String updatedBy;
	@Column(name="updated_time")
	private Date updatedTime;
	@Transient
	private String message;

	
	public SubjectResourceId getSubjectResourceId() {
		return SubjectResourceId;
	}
	@JsonProperty("SubjectResourceId")
	public void setSubjectResourceId(SubjectResourceId subjectResourceId) {
		SubjectResourceId = subjectResourceId;
	}
	public String getResourceType() {
		return ResourceType;
	}
	@JsonProperty("ResourceType")
	public void setResourceType(String resourceType) {
		ResourceType = resourceType;
	}
	public String getThumbnailImage() {
		return ThumbnailImage;
	}
	@JsonProperty("ThumbnailImage")
	public void setThumbnailImage(String thumbnailImage) {
		ThumbnailImage = thumbnailImage;
	}
	public String getResourcePath() {
		return ResourcePath;
	}
	@JsonProperty("ResourcePath")
	public void setResourcePath(String resourcePath) {
		ResourcePath = resourcePath;
	}
	public String getResourceLink() {
		return ResourceLink;
	}
	@JsonProperty("ResourceLink")
	public void setResourceLink(String resourceLink) {
		ResourceLink = resourceLink;
	}
	public String getResourceTitle() {
		return ResourceTitle;
	}
	@JsonProperty("ResourceTitle")
	public void setResourceTitle(String resourceTitle) {
		ResourceTitle = resourceTitle;
	}
	public String getResourceExtension() {
		return ResourceExtension;
	}
	@JsonProperty("ResourceExtension")
	public void setResourceExtension(String resourceExtension) {
		ResourceExtension = resourceExtension;
	}
	public int getIs_soft_delete() {
		return is_soft_delete;
	}
	public void setIs_soft_delete(int is_soft_delete) {
		this.is_soft_delete = is_soft_delete;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
