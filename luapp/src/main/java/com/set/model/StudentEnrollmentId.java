package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentEnrollmentId implements Serializable {
	@Column(name = "student_id")
	private String studentId;
	@Column(name = "class_id")
	private String classId;
	@Column(name = "section_id")
	private String sectionId;

	public StudentEnrollmentId() {
	}

	public StudentEnrollmentId(String studentId, String classId, String sectionId) {
		this.studentId = studentId;
		this.classId = classId;
		this.sectionId = sectionId;
	}

	public String getStudentId() {
		return studentId;
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
		if (!(o instanceof StudentEnrollmentId))
			return false;
		StudentEnrollmentId that = (StudentEnrollmentId) o;
		return Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getClassId(), that.getClassId())
				&& Objects.equals(getSectionId(), that.getSectionId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStudentId(), getClassId(), getSectionId());
	}
}
