package com.set.model;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "lutbl_user_reg")
public class UserRegistration {

    @Id
	@Column(name = "user_id", columnDefinition = "VARCHAR(16)")
	private String userId;
	@Column(name = "role_id", columnDefinition = "VARCHAR(1)")
	private String roleId;
	@Column(name = "email_id", columnDefinition = "VARCHAR(45)")
	private String emailId;
	@Column(name = "first_name", columnDefinition = "VARCHAR(80)")
	private String firstName;
	@Column(name = "mid_name", columnDefinition = "VARCHAR(80)")
	private String middleName;
	@Column(name = "last_name", columnDefinition = "VARCHAR(80)")
	private String lastName;
	@Column(name = "dob")
	private Date dateOfBirth;
	@Column(name = "mobile_phone", columnDefinition = "VARCHAR(10)")
	private String mobilePhone;
	@Column(name = "alt_phone", columnDefinition = "VARCHAR(10)")
	private String alterPhone;
	@Column(name = "crsp_add1", columnDefinition = "VARCHAR(45)")
	private String correspondenceAddress1;
	@Column(name = "crsp_add2", columnDefinition = "VARCHAR(45)")
	private String correspondenceAddress2;
	@Column(name = "crsp_add3", columnDefinition = "VARCHAR(45)")
	private String correspondenceAddress3;
	@Column(name = "crsp_city", columnDefinition = "VARCHAR(45)")
	private String correspondenceCity;
	@Column(name = "crsp_state", columnDefinition = "VARCHAR(45)")
	private String correspondenceState;
	@Column(name = "per_zip", columnDefinition = "VARCHAR(45)")
	private String permanentZip;
	@Column(name = "crsp_zip", columnDefinition = "VARCHAR(45)")
	private String correspondenceZip;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getAlterPhone() {
		return alterPhone;
	}
	public void setAlterPhone(String alterPhone) {
		this.alterPhone = alterPhone;
	}
	public String getCorrespondenceAddress1() {
		return correspondenceAddress1;
	}
	public void setCorrespondenceAddress1(String correspondenceAddress1) {
		this.correspondenceAddress1 = correspondenceAddress1;
	}
	public String getCorrespondenceAddress2() {
		return correspondenceAddress2;
	}
	public void setCorrespondenceAddress2(String correspondenceAddress2) {
		this.correspondenceAddress2 = correspondenceAddress2;
	}
	public String getCorrespondenceAddress3() {
		return correspondenceAddress3;
	}
	public void setCorrespondenceAddress3(String correspondenceAddress3) {
		this.correspondenceAddress3 = correspondenceAddress3;
	}
	public String getCorrespondenceCity() {
		return correspondenceCity;
	}
	public void setCorrespondenceCity(String correspondenceCity) {
		this.correspondenceCity = correspondenceCity;
	}
	public String getCorrespondenceState() {
		return correspondenceState;
	}
	public void setCorrespondenceState(String correspondenceState) {
		this.correspondenceState = correspondenceState;
	}
	public String getPermanentZip() {
		return permanentZip;
	}
	public void setPermanentZip(String permanentZip) {
		this.permanentZip = permanentZip;
	}
	public String getCorrespondenceZip() {
		return correspondenceZip;
	}
	public void setCorrespondenceZip(String correspondenceZip) {
		this.correspondenceZip = correspondenceZip;
	}
	
	
}
