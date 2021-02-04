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
public class ClassSubjectMasterDto implements Serializable {

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
	private String Message = "";

	public ClassSubjectMasterDto() {
		super();
	}

	public ClassSubjectMasterDto(String subjectName, String subjectCode, String teacherEmail, String teacherFirstName,
			String teacherMiddleName, String teacherLastName, String message) {
		super();
		SubjectName = subjectName;
		SubjectCode = subjectCode;
		TeacherEmail = teacherEmail;
		TeacherFirstName = teacherFirstName;
		TeacherMiddleName = teacherMiddleName;
		TeacherLastName = teacherLastName;
		this.Message = message;
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

	public String getMessage() {
		return Message;
	}

	@JsonProperty("Message")
	public void setMessage(String message) {
		this.Message = message;
	}
}
