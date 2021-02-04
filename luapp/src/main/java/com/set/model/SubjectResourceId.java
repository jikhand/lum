package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class SubjectResourceId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5556006886183934701L;
	@Column(name = "subj_id", columnDefinition = "VARCHAR(16)")
	private String SubjectId;
	@Column(name = "unit_id", columnDefinition = "VARCHAR(16)")
	private String UnitId;
	@Column(name = "resource_id", columnDefinition = "VARCHAR(16)")
	private String ResourceId;

	public SubjectResourceId() {
	}

	public SubjectResourceId(String subjectId, String unitId, String resourceId) {
		this.ResourceId = resourceId;
		this.SubjectId = subjectId;
		this.UnitId = unitId;
		
	}

	public String getSubjectId() {
		return SubjectId;
	}

	public String getUnitId() {
		return UnitId;
	}

	public String getResourceId() {
		return ResourceId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		this.SubjectId = subjectId;
	}

	@JsonProperty("UnitId")
	public void setUnitId(String unitId) {
		this.UnitId = unitId;
	}

	@JsonProperty("ResourceId")
	public void setTopicId(String resourceId) {
		this.ResourceId = resourceId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SubjectResourceId))
			return false;
		SubjectResourceId that = (SubjectResourceId) o;
		return Objects.equals(getResourceId(), that.getResourceId()) && Objects.equals(getSubjectId(), that.getSubjectId())
                && Objects.equals(getUnitId(), that.getUnitId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getResourceId(),getSubjectId(), getUnitId());
	}
}
