package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentClassSectionId {
	private ClassSectionMasterId ClassSectionMasterId;
	private String UserId;

	public ClassSectionMasterId getClassSectionMasterId() {
		return ClassSectionMasterId;
	}

	@JsonProperty("ClassSectionMasterId")
	public void setClassSectionMasterId(ClassSectionMasterId classSectionMasterId) {
		ClassSectionMasterId = classSectionMasterId;
	}

	public String getStudentId() {
		return UserId;
	}

	@JsonProperty("UserId")
	public void setStudentId(String studentId) {
		UserId = studentId;
	}
}
