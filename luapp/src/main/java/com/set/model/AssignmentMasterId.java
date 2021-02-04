package com.set.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssignmentMasterId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6054206472614373644L;
	@Column(name="assgn_id",columnDefinition = "VARCHAR(16)")
	private String assignmentId;
	@Column(name = "class_id")
	private String classId;
	@Column(name = "section_id")
	private String sectionId;

	public AssignmentMasterId() {
	}

	public AssignmentMasterId(String assignmentId, String classId, String sectionId) {
		this.assignmentId = assignmentId;
		this.classId = classId;
		this.sectionId = sectionId;
	}

	public String getAssignmentId() {
		return assignmentId;
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
		if (!(o instanceof AssignmentMasterId))
			return false;
		AssignmentMasterId that = (AssignmentMasterId) o;
		return Objects.equals(getAssignmentId(), that.getAssignmentId()) && Objects.equals(getClassId(), that.getClassId())
				&& Objects.equals(getSectionId(), that.getSectionId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAssignmentId(), getClassId(), getSectionId());
	}
}
