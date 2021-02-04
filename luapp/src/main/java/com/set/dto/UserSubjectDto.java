/**
 * 
 */
package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Administrator
 *
 */
public class UserSubjectDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675483915643666107L;
	private String SubjectName = "";
	private String SubjectCode = "";
	private String TeacherEmail = "";
	private String TeacherFirstName = "";
	private String TeacherMiddleName = "";
	private String TeacherLastName = "";

	public UserSubjectDto() {
		super();
	}

	public UserSubjectDto(String subjectName, String subjectCode, String teacherEmail, String teacherFirstName,
			String teacherMiddleName, String teacherLastName, String message) {
		super();
		SubjectName = subjectName;
		SubjectCode = subjectCode;
		TeacherEmail = teacherEmail;
		TeacherFirstName = teacherFirstName;
		TeacherMiddleName = teacherMiddleName;
		TeacherLastName = teacherLastName;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getSubjectCode() {
		return SubjectCode;
	}

	@JsonProperty("SubjectCode")
	public void setSubjectCode(String subjectCode) {
		SubjectCode = subjectCode;
	}

	public String getTeacherEmail() {
		return TeacherEmail;
	}

	@JsonProperty("TeacherEmail")
	public void setTeacherEmail(String teacherEmail) {
		TeacherEmail = teacherEmail;
	}

	public String getTeacherFirstName() {
		return TeacherFirstName;
	}

	@JsonProperty("TeacherFirstName")
	public void setTeacherFirstName(String teacherFirstName) {
		TeacherFirstName = teacherFirstName;
	}

	public String getTeacherMiddleName() {
		return TeacherMiddleName;
	}

	@JsonProperty("TeacherMiddleName")
	public void setTeacherMiddleName(String teacherMiddleName) {
		TeacherMiddleName = teacherMiddleName;
	}

	public String getTeacherLastName() {
		return TeacherLastName;
	}

	@JsonProperty("TeacherLastName")
	public void setTeacherLastName(String teacherLastName) {
		TeacherLastName = teacherLastName;
	}
}
