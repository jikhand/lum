package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class StudentAnswerDetailsDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8513887295140667406L;
	private List<StudentAnswerDto> StudentAnswerList;
	private String StudentAnswerTypeName;
	private String StudentAnswerTypeId;
	private String PartName;
	private String Marks;
	
	public List<StudentAnswerDto> getStudentAnswerList() {
		return StudentAnswerList;
	}
	@JsonProperty("TestData")
	public void setStudentAnswerList(List<StudentAnswerDto> studentAnswerList) {
		StudentAnswerList = studentAnswerList;
	}
	public String getStudentAnswerTypeName() {
		return StudentAnswerTypeName;
	}
	@JsonProperty("StudentAnswerTypeName")
	public void setStudentAnswerTypeName(String studentAnswerTypeName) {
		StudentAnswerTypeName = studentAnswerTypeName;
	}
	public String getStudentAnswerTypeId() {
		return StudentAnswerTypeId;
	}
	@JsonProperty("StudentAnswerTypeId")
	public void setStudentAnswerTypeId(String studentAnswerTypeId) {
		StudentAnswerTypeId = studentAnswerTypeId;
	}
	public String getPartName() {
		return PartName;
	}
	@JsonProperty("PartName")
	public void setPartName(String partName) {
		PartName = partName;
	}
	public String getMarks() {
		return Marks;
	}
	@JsonProperty("Marks")
	public void setMarks(String marks) {
		Marks = marks;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
