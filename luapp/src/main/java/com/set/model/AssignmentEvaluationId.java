package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssignmentEvaluationId implements Serializable {

	@Column(name = "student_id", columnDefinition = "VARCHAR(8)")
	private String studentId;
	@Column(name = "assgn_id", columnDefinition = "VARCHAR(8)")
	private String assignId;

	public AssignmentEvaluationId() {
	}

	public AssignmentEvaluationId(String studentId, String assignId) {
		this.studentId = studentId;
		this.assignId = assignId;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getAssignId() {
		return assignId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AssignmentEvaluationId))
			return false;
		AssignmentEvaluationId that = (AssignmentEvaluationId) o;
		return Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getAssignId(), that.getAssignId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStudentId(), getAssignId());
	}
}
