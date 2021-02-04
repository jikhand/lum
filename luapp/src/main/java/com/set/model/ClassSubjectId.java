package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClassSubjectId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -17367754195279340L;
	@Column(name = "class_id", columnDefinition = "VARCHAR(8)")
	private String classId;
	@Column(name = "section_id", columnDefinition = "VARCHAR(8)")
	private String sectionId;
	@Column(name = "subj_id", columnDefinition = "VARCHAR(16)")
	private String subjectId;
	@Column(name = "teacher_id", columnDefinition = "VARCHAR(16)")
	private String teacherId;
	public ClassSubjectId() {
	}

	public ClassSubjectId(String classId, String sectionId, String subjectId, String teacherId) {
		this.classId = classId;
		this.sectionId = sectionId;
		this.subjectId = subjectId;
		this.teacherId = teacherId;
	}

	public String getClassId() {
		return classId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ClassSubjectId))
			return false;
		ClassSubjectId that = (ClassSubjectId) o;
		return Objects.equals(getClassId(), that.getClassId()) && Objects.equals(getSectionId(), that.getSectionId()) && Objects.equals(getSubjectId(), that.getSubjectId()) && Objects.equals(getTeacherId(), that.getTeacherId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClassId(), getSectionId(), getSubjectId(), getTeacherId());
	}
}
