package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class TeacherResourceBankUnitDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4171909976974386635L;
	private String UnitSubjectId;
	private String UnitName;
	private List<TeacherResourceBankDto> TeacherResourceBankList;

	public String getUnitSubjectId() {
		return UnitSubjectId;
	}

	@JsonProperty("UnitSubjectId")
	public void setUnitSubjectId(String unitSubjectId) {
		UnitSubjectId = unitSubjectId;
	}

	public String getUnitName() {
		return UnitName;
	}

	@JsonProperty("UnitName")
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}


	public List<TeacherResourceBankDto> getTeacherResourceBankList() {
		return TeacherResourceBankList;
	}

	@JsonProperty("resourceresult")
	public void setTeacherResourceBankList(List<TeacherResourceBankDto> teacherResourceBankList) {
		TeacherResourceBankList = teacherResourceBankList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
