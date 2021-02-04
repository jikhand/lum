package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResourceBankDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6925218744187523980L;
	private String ResourceId;
	private String ResourceExtension;
	private String ResourceType;
	private String ThumbnailImage;
	private String ResourceTitle;
	private String ResourcePath;
	private String ResourceLink;
	private String SubjectId;
	private String UnitId;

	public String getResourceId() {
		return ResourceId;
	}

	@JsonProperty("ResourceId")
	public void setResourceId(String resourceId) {
		ResourceId = resourceId;
	}

	public String getResourceExtension() {
		return ResourceExtension;
	}

	@JsonProperty("ResourceExtension")
	public void setResourceExtension(String resourceExtension) {
		ResourceExtension = resourceExtension;
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

	public String getResourceTitle() {
		return ResourceTitle;
	}

	@JsonProperty("ResourceTitle")
	public void setResourceTitle(String resourceTitle) {
		ResourceTitle = resourceTitle;
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
	
	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getUnitId() {
		return UnitId;
	}

	@JsonProperty("UnitId")
	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
