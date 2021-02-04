package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class ClassSectionMasterId implements Serializable {
	@Column(name = "class_id", columnDefinition = "VARCHAR(8)")
	private String classId;
	@Column(name = "section_id", columnDefinition = "VARCHAR(8)")
	private String sectionId;

	public ClassSectionMasterId() {
	}

	public ClassSectionMasterId(String classId, String sectionId) {
		this.classId = classId;
		this.sectionId = sectionId;
	}

	public String getClassId() {
		return classId;
	}

	public String getSectionId() {
		return sectionId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ClassSectionMasterId))
			return false;
		ClassSectionMasterId that = (ClassSectionMasterId) o;
		return Objects.equals(getClassId(), that.getClassId()) && Objects.equals(getSectionId(), that.getSectionId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClassId(), getSectionId());
	}
}
