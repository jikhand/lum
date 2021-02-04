package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherResourceBankSubjectDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8441049231529471501L;
	private String SubjectId;
	private String SubjectName;
	private List<TeacherResourceBankUnitDto> TeacherResourceBankUnitList;
	

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public List<TeacherResourceBankUnitDto> getTeacherResourceBankUnitList() {
		return TeacherResourceBankUnitList;
	}

	@JsonProperty("unitdata")
	public void setTeacherResourceBankUnitList(List<TeacherResourceBankUnitDto> teacherResourceBankUnitList) {
		TeacherResourceBankUnitList = teacherResourceBankUnitList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
