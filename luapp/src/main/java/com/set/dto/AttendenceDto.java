package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendenceDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4657341741429417680L;
	/**
	 * 
	 */
	
	private String ClassId;
	private String ClassName;
	private String Id;
	private String StudentAddress;
	private String StudentEmail;
	private String StudentFirstName;
	private String StudentLastName;
	private String StudentMiddleName;
	private String StudentMobileNumber;
	private String StudentProfileImage;
	private String StudentRollNumber;
	private String Message;
	
	
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
	public String getId() {
		return Id;
	}
	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}
	public String getStudentAddress() {
		return StudentAddress;
	}
	@JsonProperty("StudentAddress")
	public void setStudentAddress(String studentAddress) {
		StudentAddress = studentAddress;
	}
	public String getStudentEmail() {
		return StudentEmail;
	}
	@JsonProperty("StudentEmail")
	public void setStudentEmail(String studentEmail) {
		StudentEmail = studentEmail;
	}
	public String getStudentFirstName() {
		return StudentFirstName;
	}
	@JsonProperty("StudentFirstName")
	public void setStudentFirstName(String studentFirstName) {
		StudentFirstName = studentFirstName;
	}
	public String getStudentLastName() {
		return StudentLastName;
	}
	@JsonProperty("StudentLastName")
	public void setStudentLastName(String studentLastName) {
		StudentLastName = studentLastName;
	}
	public String getStudentMiddleName() {
		return StudentMiddleName;
	}
	@JsonProperty("StudentMiddleName")
	public void setStudentMiddleName(String studentMiddleName) {
		StudentMiddleName = studentMiddleName;
	}
	public String getStudentMobileNumber() {
		return StudentMobileNumber;
	}
	@JsonProperty("StudentMobileNumber")
	public void setStudentMobileNumber(String studentMobileNumber) {
		StudentMobileNumber = studentMobileNumber;
	}
	public String getStudentProfileImage() {
		return StudentProfileImage;
	}
	@JsonProperty("StudentProfileImage")
	public void setStudentProfileImage(String studentProfileImage) {
		StudentProfileImage = studentProfileImage;
	}
	public String getStudentRollNumber() {
		return StudentRollNumber;
	}
	@JsonProperty("StudentRollNumber")
	public void setStudentRollNumber(String studentRollNumber) {
		StudentRollNumber = studentRollNumber;
	}
}
