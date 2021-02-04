package com.set.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeMasterDetailsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4854062996887206718L;
	private String CityName;
	private String CountryName;
	private String CreatedAt;
	private String DesignationData;
	private String Id;
	private String IsDeleted = "0";
	private Date LastLogin;
	private QualificationDataDto QualificationData;
	private String RoleId;
	private String RoleName;
	private String StateName;
	private String Status = "0";
	private String Token;
	private Date UpdatedAt;
	private String UserAddress;
	private Date UserDateOfBirth;
	private String UserEmail;
	private String UserFirstName;
	private String UserGender;
	private String UserLastName;
	private String UserMiddleName;
	private String UserMobileNumber;
	private String UserProfileImage;
	private String UserZipCode;
	private String UserId;
	private List<ClassSubjectDataDto> ClassSubjectData;

	public String getCityName() {
		return CityName;
	}

	@JsonProperty("CityName")
	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public String getCountryName() {
		return CountryName;
	}

	@JsonProperty("CountryName")
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	public String getCreatedAt() {
		return CreatedAt;
	}

	@JsonProperty("CreatedAt")
	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
	}

	public String getDesignationData() {
		return DesignationData;
	}

	@JsonProperty("DesignationData")
	public void setDesignationData(String designationData) {
		DesignationData = designationData;
	}

	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public String getIsDeleted() {
		return IsDeleted;
	}

	@JsonProperty("IsDeleted")
	public void setIsDeleted(String isDeleted) {
		IsDeleted = isDeleted;
	}

	public Date getLastLogin() {
		return LastLogin;
	}

	@JsonProperty("LastLogin")
	public void setLastLogin(Date lastLogin) {
		LastLogin = lastLogin;
	}

	public QualificationDataDto getQualificationData() {
		return QualificationData;
	}

	@JsonProperty("QualificationData")
	public void setQualificationData(QualificationDataDto qualificationData) {
		QualificationData = qualificationData;
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

	public String getStateName() {
		return StateName;
	}

	@JsonProperty("StateName")
	public void setStateName(String stateName) {
		StateName = stateName;
	}

	public String isStatus() {
		return Status;
	}

	@JsonProperty("ClassId")
	public void setStatus(String status) {
		Status = status;
	}

	public String getToken() {
		return Token;
	}

	@JsonProperty("Token")
	public void setToken(String token) {
		Token = token;
	}

	public Date getUpdatedAt() {
		return UpdatedAt;
	}

	@JsonProperty("UpdatedAt")
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}

	public String getUserAddress() {
		return UserAddress;
	}

	@JsonProperty("UserAddress")
	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}

	public Date getUserDateOfBirth() {
		return UserDateOfBirth;
	}

	@JsonProperty("UserDateOfBirth")
	public void setUserDateOfBirth(Date userDateOfBirth) {
		UserDateOfBirth = userDateOfBirth;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	@JsonProperty("UserEmail")
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String getUserFirstName() {
		return UserFirstName;
	}

	@JsonProperty("UserFirstName")
	public void setUserFirstName(String userFirstName) {
		UserFirstName = userFirstName;
	}

	public String getUserGender() {
		return UserGender;
	}

	@JsonProperty("UserGender")
	public void setUserGender(String userGender) {
		UserGender = userGender;
	}

	public String getUserLastName() {
		return UserLastName;
	}

	@JsonProperty("UserLastName")
	public void setUserLastName(String userLastName) {
		UserLastName = userLastName;
	}

	public String getUserMiddleName() {
		return UserMiddleName;
	}

	@JsonProperty("UserMiddleName")
	public void setUserMiddleName(String userMiddleName) {
		UserMiddleName = userMiddleName;
	}

	public String getUserMobileNumber() {
		return UserMobileNumber;
	}

	@JsonProperty("UserMobileNumber")
	public void setUserMobileNumber(String userMobileNumber) {
		UserMobileNumber = userMobileNumber;
	}

	public String getUserProfileImage() {
		return UserProfileImage;
	}

	@JsonProperty("UserProfileImage")
	public void setUserProfileImage(String userProfileImage) {
		UserProfileImage = userProfileImage;
	}

	public String getUserZipCode() {
		return UserZipCode;
	}

	@JsonProperty("UserZipCode")
	public void setUserZipCode(String userZipCode) {
		UserZipCode = userZipCode;
	}


	public List<ClassSubjectDataDto> getClassSubjectData() {
		return ClassSubjectData;
	}

	@JsonProperty("ClassSubjectData")
	public void setClassSubjectData(List<ClassSubjectDataDto> classSubjectData) {
		ClassSubjectData = classSubjectData;
	}

	public String getUserId() {
		return UserId;
	}
	@JsonProperty("UserId")
	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getStatus() {
		return Status;
	}
}
