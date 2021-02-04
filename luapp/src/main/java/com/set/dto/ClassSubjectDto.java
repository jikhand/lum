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
public class ClassSubjectDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675483915643666107L;
	private String ClassId = "";
	private String SectionId = "";
	private String SubjectId = "";
	private String SubjectName = "";
	private String SubjectCode = "";
	private String TeacherId = "";
	private String AcademicYear = "";
	private String TeacherEmail = "";
	private String TeacherFirstName = "";
	private String TeacherMiddleName = "";
	private String TeacherLastName = "";
	private String Message = "";

	public ClassSubjectDto() {
		super();
	}

	public ClassSubjectDto(String subjectName, String subjectCode, String teacherEmail, String teacherFirstName,
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

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getSectionId() {
		return SectionId;
	}

	@JsonProperty("SectionId")
	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}

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

	public String getTeacherId() {
		return TeacherId;
	}
	@JsonProperty("TeacherId")
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

	public String getAcademicYear() {
		return AcademicYear;
	}
	@JsonProperty("AcademicYear")
	public void setAcademicYear(String academicYear) {
		AcademicYear = academicYear;
	}

	public String getMessage() {
		return Message;
	}

	@JsonProperty("Message")
	public void setMessage(String message) {
		this.Message = message;
	}
}
