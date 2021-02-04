package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class SubjectUnitMasterId implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 3262946284848166483L;
	@Column(name = "subj_id", columnDefinition = "VARCHAR(16)")
	private String SubjectId;
	@Column(name = "unit_id", columnDefinition = "VARCHAR(16)")
	private String UnitId;
	@Column(name = "topic_id", columnDefinition = "VARCHAR(16)")
	private String TopicId;

	public SubjectUnitMasterId() {
	}

	public SubjectUnitMasterId(String subjectId, String unitId, String topicId) {
		this.SubjectId = subjectId;
		this.UnitId = unitId;
		this.TopicId = topicId;
	}

	public String getSubjectId() {
		return SubjectId;
	}

	public String getUnitId() {
		return UnitId;
	}

	public String getTopicId() {
		return TopicId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		this.SubjectId = subjectId;
	}

	@JsonProperty("UnitId")
	public void setUnitId(String unitId) {
		this.UnitId = unitId;
	}

	@JsonProperty("TopicId")
	public void setTopicId(String topicId) {
		this.TopicId = topicId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SubjectUnitMasterId))
			return false;
		SubjectUnitMasterId that = (SubjectUnitMasterId) o;
		return Objects.equals(getSubjectId(), that.getSubjectId()) && Objects.equals(getUnitId(), that.getUnitId())
				&& Objects.equals(getTopicId(), that.getTopicId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSubjectId(), getUnitId(), getTopicId());
	}
}
