package com.set.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMasterDetailsDto implements Serializable {

	private static final long serialVersionUID = -8285618620796913309L;

	private Date AdmissionDate;
	private String CityId;
	private String CityName;
	private String ClassId;
	private String ClassName;
	private String CountryId;
	private String CountryName;
	private Date CreatedAt;
	private Date DateOfBirth;
	private String DocumentsImage;
	private BigInteger EmergencyContactNumber;
	private String Gender;
	private String GradeCompleted;
	private String HostelId;
	private String Id;
	private boolean IsDeleted;
	private Date LastLogin;
	private String LibraryId;
	private String ParentId;
	private String PreviousSchool;
	private String RoleId;
	private String RoleName;
	private String SectionId;
	private String SectionName;
	private String StateId;
	private String StateName;
	private boolean Status;
	private String StudentAddress;
	private String StudentEmail;
	private String StudentFirstName;
	private String StudentId;
	private String StudentLastName;
	private String StudentMiddleName;
	private int StudentMobileNumber;
	private String StudentPassword;
	private String StudentProfileImage;
	private String StudentRollNumber;
	private String StudentZipCode;
	private int TelephoneNumber;
	private String Token;
	private List<UserSubjectDto> SubjectData;
	
	public Date getAdmissionDate() {
		return AdmissionDate;
	}

	@JsonProperty("AdmissionDate")
	public void setAdmissionDate(Date admissionDate) {
		AdmissionDate = admissionDate;
	}

	public String getCityId() {
		return CityId;
	}

	@JsonProperty("CityId")
	public void setCityId(String cityId) {
		CityId = cityId;
	}

	public String getCityName() {
		return CityName;
	}

	@JsonProperty("CityName")
	public void setCityName(String cityName) {
		CityName = cityName;
	}

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

	public String getCountryId() {
		return CountryId;
	}

	@JsonProperty("CountryId")
	public void setCountryId(String countryId) {
		CountryId = countryId;
	}

	public String getCountryName() {
		return CountryName;
	}

	@JsonProperty("CountryName")
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	@JsonProperty("CreatedAt")
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	@JsonProperty("DateOfBirth")
	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getDocumentsImage() {
		return DocumentsImage;
	}

	@JsonProperty("DocumentsImage")
	public void setDocumentsImage(String documentsImage) {
		DocumentsImage = documentsImage;
	}

	public BigInteger getEmergencyContactNumber() {
		return EmergencyContactNumber;
	}

	@JsonProperty("EmergencyContactNumber")
	public void setEmergencyContactNumber(BigInteger emergencyContactNumber) {
		EmergencyContactNumber = emergencyContactNumber;
	}

	public String getGender() {
		return Gender;
	}

	@JsonProperty("Gender")
	public void setGender(String gender) {
		Gender = gender;
	}

	
	public String getGradeCompleted() {
		return GradeCompleted;
	}

	@JsonProperty("GradeCompleted")
	public void setGradeCompleted(String gradeCompleted) {
		GradeCompleted = gradeCompleted;
	}

	public String getHostelId() {
		return HostelId;
	}

	@JsonProperty("HostelId")
	public void setHostelId(String hostelId) {
		HostelId = hostelId;
	}

	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	@JsonProperty("IsDeleted")
	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public Date getLastLogin() {
		return LastLogin;
	}
	
	@JsonProperty("LastLogin")
	public void setLastLogin(Date lastLogin) {
		LastLogin = lastLogin;
	}

	public String getLibraryId() {
		return LibraryId;
	}
	@JsonProperty("LibraryId")
	public void setLibraryId(String libraryId) {
		LibraryId = libraryId;
	}

	public String getParentId() {
		return ParentId;
	}

	@JsonProperty("ParentId")
	public void setParentId(String parentId) {
		ParentId = parentId;
	}

	public String getPreviousSchool() {
		return PreviousSchool;
	}

	@JsonProperty("PreviousSchool")
	public void setPreviousSchool(String previousSchool) {
		PreviousSchool = previousSchool;
	}

	public String getRoleId() {
		return RoleId;
	}

	@JsonProperty("RoleId")
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}

	public String getRoleName() {
		return RoleName;
	}

	@JsonProperty("RoleName")
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	public String getSectionId() {
		return SectionId;
	}

	@JsonProperty("SectionId")
	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}

	public String getSectionName() {
		return SectionName;
	}

	@JsonProperty("SectionName")
	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}

	public String getStateId() {
		return StateId;
	}

	@JsonProperty("StateId")
	public void setStateId(String stateId) {
		StateId = stateId;
	}

	public String getStateName() {
		return StateName;
	}

	@JsonProperty("StateName")
	public void setStateName(String stateName) {
		StateName = stateName;
	}

	public boolean isStatus() {
		return Status;
	}

	@JsonProperty("Status")
	public void setStatus(boolean status) {
		Status = status;
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

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
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

	public int getStudentMobileNumber() {
		return StudentMobileNumber;
	}

	@JsonProperty("StudentMobileNumber")
	public void setStudentMobileNumber(int studentMobileNumber) {
		StudentMobileNumber = studentMobileNumber;
	}

	
	public String getStudentPassword() {
		return StudentPassword;
	}

	@JsonProperty("StudentPassword")
	public void setStudentPassword(String studentPassword) {
		StudentPassword = studentPassword;
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

	public String getStudentZipCode() {
		return StudentZipCode;
	}

	@JsonProperty("StudentZipCode")
	public void setStudentZipCode(String studentZipCode) {
		StudentZipCode = studentZipCode;
	}

	public int getTelephoneNumber() {
		return TelephoneNumber;
	}

	@JsonProperty("TelephoneNumber")
	public void setTelephoneNumber(int telephoneNumber) {
		TelephoneNumber = telephoneNumber;
	}

	public String getToken() {
		return Token;
	}
	
	@JsonProperty("Token")
	public void setToken(String token) {
		Token = token;
	}

	public List<UserSubjectDto> getSubjectData() {
		return SubjectData;
	}

	@JsonProperty("SubjectData")
	public void setSubjectData(List<UserSubjectDto> subjectData) {
		SubjectData = subjectData;
	}

	
	
}
