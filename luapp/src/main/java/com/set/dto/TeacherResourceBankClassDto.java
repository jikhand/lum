package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherResourceBankClassDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6391017927314420638L;
	private String ClassId;
	private String ClassName;
	private List<TeacherResourceBankSubjectDto> TeacherResourceBankSubjectList;
	

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getClassName() {
		return ClassName;
	}

	@JsonProperty("ClassName")
	public void setClassName(String className) {
		ClassName = className;
	}

	public List<TeacherResourceBankSubjectDto> getTeacherResourceBankSubjectList() {
		return TeacherResourceBankSubjectList;
	}

	@JsonProperty("subjectdata")
	public void setTeacherResourceBankSubjectList(List<TeacherResourceBankSubjectDto> teacherResourceBankSubjectList) {
		TeacherResourceBankSubjectList = teacherResourceBankSubjectList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
