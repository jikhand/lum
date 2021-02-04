package com.set.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_stdnt_master")
public class StudentMaster {

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	@Id
	@Column(name = "student_id", columnDefinition = "VARCHAR(16)")
	private String studentId;

	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "role_id")
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	@OneToOne(targetEntity = StudentEnrollment.class, fetch = FetchType.EAGER)
//	@JoinColumns({ @JoinColumn(nullable = false, name = "studentId"), @JoinColumn(nullable = false, name = "class_id"),
//			@JoinColumn(nullable = false, name = "section_id") })
//	private StudentEnrollment studentEnrollment;
//
//	public StudentEnrollment getStudentEnrollment() {
//		return studentEnrollment;
//	}
//
//	public void setStudentEnrollment(StudentEnrollment studentEnrollment) {
//		this.studentEnrollment = studentEnrollment;
//	}

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "userId")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "first_name", columnDefinition = "VARCHAR(80)")
	private String firstName;
	@Column(name = "mid_name", columnDefinition = "VARCHAR(80)")
	private String middleName;
	@Column(name = "last_name", columnDefinition = "VARCHAR(80)")
	private String lastName;
	@Column(name = "dob")
	private Date dateOfBirth;
	@Column(name = "gender", columnDefinition = "VARCHAR(6)")
	private String gender;
	@Column(name = "email_id", columnDefinition = "VARCHAR(45)")
	private String emailId;
	@Column(name = "profile_pic", columnDefinition = "VARCHAR(240)")
	private String profilePicture;
	@Column(name = "mobile_phone", columnDefinition = "VARCHAR(10)")
	private String mobilePhone;
	@Column(name = "alt_phone", columnDefinition = "VARCHAR(10)")
	private String alterPhone;
	@Column(name = "per_addr_line1", columnDefinition = "VARCHAR(80)")
	private String permanentAddressLine1;
	@Column(name = "per_addr_line2", columnDefinition = "VARCHAR(80)")
	private String permanentAddressLine2;
	@Column(name = "per_addr_line3", columnDefinition = "VARCHAR(80)")
	private String permanentAddressLine3;
	@Column(name = "per_City", columnDefinition = "VARCHAR(45)")
	private String permanentCity;
	@Column(name = "per_state", columnDefinition = "VARCHAR(45)")
	private String permanentState;
	@Column(name = "crsp_add1", columnDefinition = "VARCHAR(80)")
	private String correspondenceAddress1;
	@Column(name = "crsp_add2", columnDefinition = "VARCHAR(80)")
	private String correspondenceAddress2;
	@Column(name = "crsp_add3", columnDefinition = "VARCHAR(80)")
	private String correspondenceAddress3;
	@Column(name = "crsp_city", columnDefinition = "VARCHAR(45)")
	private String correspondenceCity;
	@Column(name = "crsp_state", columnDefinition = "VARCHAR(45)")
	private String correspondenceState;
	@Column(name = "per_zip", columnDefinition = "int(10)")
	private int permanentZip;
	@Column(name = "crsp_zip", columnDefinition = "int(10)")
	private int correspondenceZip;
	@Column(name = "father_fname", columnDefinition = "VARCHAR(80)")
	private String fatherFirstName;
	@Column(name = "father_mname", columnDefinition = "VARCHAR(80)")
	private String fatherMiddleName;
	@Column(name = "father_lname", columnDefinition = "VARCHAR(80)")
	private String fatherLastName;
	@Column(name = "mother_fname", columnDefinition = "VARCHAR(80)")
	private String motherFirstName;
	@Column(name = "mother_mname", columnDefinition = "VARCHAR(80)")
	private String motherMiddleName;
	@Column(name = "mother_lname", columnDefinition = "VARCHAR(80)")
	private String motherLastName;
	@Column(name = "guardian_fname", columnDefinition = "VARCHAR(80)")
	private String guardianFirstName;
	@Column(name = "guardian_mname", columnDefinition = "VARCHAR(80)")
	private String guardianMiddleName;
	@Column(name = "guardian_lname", columnDefinition = "VARCHAR(80)")
	private String guardianLastName;
	@Column(name = "father_contact", columnDefinition = "VARCHAR(12)")
	private String fatherContact;
	@Column(name = "mother_contact", columnDefinition = "VARCHAR(12)")
	private String motherContact;
	@Column(name = "guardian_contact", columnDefinition = "VARCHAR(12)")
	private String guardianContact;
	@Column(name = "admission_date")
	private Date admissionDate;
	@Column(name = "prv_school", columnDefinition = "VARCHAR(80)")
	private String previousSchool;
	@Column(name = "class_id", columnDefinition = "VARCHAR(255)")
	private String classId;
	@Column(name = "section_id", columnDefinition = "VARCHAR(255)")
	private String sectionId;
	@Column(name = "for_use_field1", columnDefinition = "VARCHAR(45)")
	private String forUseField1;
	@Column(name = "for_use_field2", columnDefinition = "VARCHAR(45)")
	private String forUseField2;
	@Column(name = "for_use_field3", columnDefinition = "VARCHAR(45)")
	private String forUseField3;
	@Column(name = "for_use_field4", columnDefinition = "VARCHAR(45)")
	private String forUseField4;
	@Column(name = "for_use_field5", columnDefinition = "VARCHAR(45)")
	private String forUseField5;
	@Column(name = "for_use_field6", columnDefinition = "VARCHAR(45)")
	private String forUseField6;
	@Column(name = "is_soft_delete", columnDefinition = "int(1)")
	private int is_soft_delete;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(80)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(80)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;
	@Column(name = "country_id", columnDefinition = "VARCHAR(45)")
	private String countryId;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
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

	public String getPermanentAddressLine1() {
		return permanentAddressLine1;
	}

	public void setPermanentAddressLine1(String permanentAddressLine1) {
		this.permanentAddressLine1 = permanentAddressLine1;
	}

	public String getPermanentAddressLine2() {
		return permanentAddressLine2;
	}

	public void setPermanentAddressLine2(String permanentAddressLine2) {
		this.permanentAddressLine2 = permanentAddressLine2;
	}

	public String getPermanentAddressLine3() {
		return permanentAddressLine3;
	}

	public void setPermanentAddressLine3(String permanentAddressLine3) {
		this.permanentAddressLine3 = permanentAddressLine3;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
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

	public int getPermanentZip() {
		return permanentZip;
	}

	public void setPermanentZip(int permanentZip) {
		this.permanentZip = permanentZip;
	}

	public int getCorrespondenceZip() {
		return correspondenceZip;
	}

	public void setCorrespondenceZip(int correspondenceZip) {
		this.correspondenceZip = correspondenceZip;
	}

	public String getFatherFirstName() {
		return fatherFirstName;
	}

	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}

	public String getFatherMiddleName() {
		return fatherMiddleName;
	}

	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	public String getMotherFirstName() {
		return motherFirstName;
	}

	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}

	public String getMotherMiddleName() {
		return motherMiddleName;
	}

	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	public String getGuardianFirstName() {
		return guardianFirstName;
	}

	public void setGuardianFirstName(String guardianFirstName) {
		this.guardianFirstName = guardianFirstName;
	}

	public String getGuardianMiddleName() {
		return guardianMiddleName;
	}

	public void setGuardianMiddleName(String guardianMiddleName) {
		this.guardianMiddleName = guardianMiddleName;
	}

	public String getGuardianLastName() {
		return guardianLastName;
	}

	public void setGuardianLastName(String guardianLastName) {
		this.guardianLastName = guardianLastName;
	}

	public String getFatherContact() {
		return fatherContact;
	}

	public void setFatherContact(String fatherContact) {
		this.fatherContact = fatherContact;
	}

	public String getMotherContact() {
		return motherContact;
	}

	public void setMotherContact(String motherContact) {
		this.motherContact = motherContact;
	}

	public String getGuardianContact() {
		return guardianContact;
	}

	public void setGuardianContact(String guardianContact) {
		this.guardianContact = guardianContact;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getPreviousSchool() {
		return previousSchool;
	}

	public void setPreviousSchool(String previousSchool) {
		this.previousSchool = previousSchool;
	}

	public String getForUseField1() {
		return forUseField1;
	}

	public void setForUseField1(String forUseField1) {
		this.forUseField1 = forUseField1;
	}

	public String getForUseField2() {
		return forUseField2;
	}

	public void setForUseField2(String forUseField2) {
		this.forUseField2 = forUseField2;
	}

	public String getForUseField3() {
		return forUseField3;
	}

	public void setForUseField3(String forUseField3) {
		this.forUseField3 = forUseField3;
	}

	public String getForUseField4() {
		return forUseField4;
	}

	public void setForUseField4(String forUseField4) {
		this.forUseField4 = forUseField4;
	}

	public String getForUseField5() {
		return forUseField5;
	}

	public void setForUseField5(String forUseField5) {
		this.forUseField5 = forUseField5;
	}

	public String getForUseField6() {
		return forUseField6;
	}

	public void setForUseField6(String forUseField6) {
		this.forUseField6 = forUseField6;
	}

	public int getIs_soft_delete() {
		return is_soft_delete;
	}

	public void setIs_soft_delete(int is_soft_delete) {
		this.is_soft_delete = is_soft_delete;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@javax.persistence.Transient
	private String message;
	@javax.persistence.Transient
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
