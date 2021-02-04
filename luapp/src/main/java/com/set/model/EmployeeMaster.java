package com.set.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCreation;

import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_emp_master")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeMaster {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id", columnDefinition = "VARCHAR(8)")
	private String employeeId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
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

	public Date getDateOfJoining() {
		return DateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		DateOfJoining = dateOfJoining;
	}

	public Date getDateOfLeaving() {
		return dateOfLeaving;
	}

	public void setDateOfLeaving(Date dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAlternatePhone() {
		return AlternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		AlternatePhone = alternatePhone;
	}

	public String getQulaificationDegree() {
		return qulaificationDegree;
	}

	public void setQulaificationDegree(String qulaificationDegree) {
		this.qulaificationDegree = qulaificationDegree;
	}

	public String getDegreeOfSpecilization() {
		return DegreeOfSpecilization;
	}

	public void setDegreeOfSpecilization(String degreeOfSpecilization) {
		DegreeOfSpecilization = degreeOfSpecilization;
	}

	public String getUnivFrom() {
		return univFrom;
	}

	public void setUnivFrom(String univFrom) {
		this.univFrom = univFrom;
	}

	public int getDeg_pass_year() {
		return deg_pass_year;
	}

	public void setDeg_pass_year(int deg_pass_year) {
		this.deg_pass_year = deg_pass_year;
	}

	public String getAddressLine1() {
		return AddressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return AddressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return AddressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		AddressLine3 = addressLine3;
	}

	public String getPerCity() {
		return perCity;
	}

	public void setPerCity(String perCity) {
		this.perCity = perCity;
	}

	public String getPerState() {
		return perState;
	}

	public void setPerState(String perState) {
		this.perState = perState;
	}

	public String getPerZip() {
		return perZip;
	}

	public void setPerZip(String perZip) {
		this.perZip = perZip;
	}

	public String getCrspAdd1() {
		return crspAdd1;
	}

	public void setCrspAdd1(String crspAdd1) {
		this.crspAdd1 = crspAdd1;
	}

	public String getCrspAdd2() {
		return crspAdd2;
	}

	public void setCrspAdd2(String crspAdd2) {
		this.crspAdd2 = crspAdd2;
	}

	public String getCrspAdd3() {
		return crspAdd3;
	}

	public void setCrspAdd3(String crspAdd3) {
		this.crspAdd3 = crspAdd3;
	}

	public String getCrspCity() {
		return crspCity;
	}

	public void setCrspCity(String crspCity) {
		this.crspCity = crspCity;
	}

	public String getCrspState() {
		return crspState;
	}

	public void setCrspState(String crspState) {
		this.crspState = crspState;
	}

	public String getCrspZip() {
		return crspZip;
	}

	public void setCrspZip(String crspZip) {
		this.crspZip = crspZip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getMotheFirstName() {
		return motheFirstName;
	}

	public void setMotheFirstName(String motheFirstName) {
		this.motheFirstName = motheFirstName;
	}

	public String getMotherMiddleName() {
		return motherMiddleName;
	}

	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}

	public String getMotherLastname() {
		return motherLastname;
	}

	public void setMotherLastname(String motherLastname) {
		this.motherLastname = motherLastname;
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

	public String getUseField1() {
		return useField1;
	}

	public void setUseField1(String useField1) {
		this.useField1 = useField1;
	}

	public String getUseField2() {
		return useField2;
	}

	public void setUseField2(String useField2) {
		this.useField2 = useField2;
	}

	public String getUseField3() {
		return useField3;
	}

	public void setUseField3(String useField3) {
		this.useField3 = useField3;
	}

	public String getUseField4() {
		return useField4;
	}

	public void setUseField4(String useField4) {
		this.useField4 = useField4;
	}

	public String getUseField5() {
		return useField5;
	}

	public void setUseField5(String useField5) {
		this.useField5 = useField5;
	}

	public String getUseField6() {
		return useField6;
	}

	public void setUseField6(String useField6) {
		this.useField6 = useField6;
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

	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "role_id")
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "userId")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// @Column(name="user_id",columnDefinition = "VARCHAR(8)")
	// private String userId;
	@Column(name = "first_name", columnDefinition = "VARCHAR(80)")
	private String FirstName;
	@Column(name = "mid_name", columnDefinition = "VARCHAR(80)")
	private String middleName;
	@Column(name = "last_name", columnDefinition = "VARCHAR(80)")
	private String lastName;

	@Column(name = "dob", columnDefinition = "date")
	private Date dateOfBirth;
	@Column(name = "doj", columnDefinition = "date")
	private Date DateOfJoining;
	@Column(name = "dol", columnDefinition = "date")
	private Date dateOfLeaving;
	@Column(name = "gender", columnDefinition = "VARCHAR(6)")
	private String gender;
	@Column(name = "profile_pic", columnDefinition = "VARCHAR(500)")
	private String profilePic;
	@Column(name = "mobile_phone", columnDefinition = "VARCHAR(10)")
	private String mobilePhone;
	@Column(name = "Alt_phone", columnDefinition = "VARCHAR(10)")
	private String AlternatePhone;

	@Column(name = "deg_of_qual", columnDefinition = "VARCHAR(100)")
	private String qulaificationDegree;
	@Column(name = "deg_of_spl", columnDefinition = "VARCHAR(100)")
	private String DegreeOfSpecilization;
	@Column(name = "univ_from", columnDefinition = "VARCHAR(45)")
	private String univFrom;
	@Column(name = "deg_pass_year", columnDefinition = "int(10)")
	private int deg_pass_year;
	@Column(name = "per_addr_line1", columnDefinition = "VARCHAR(80)")
	private String AddressLine1;
	@Column(name = "per_addr_line2", columnDefinition = "VARCHAR(80)")
	private String AddressLine2;
	@Column(name = "per_addr_line3", columnDefinition = "VARCHAR(80)")
	private String AddressLine3;

	@Column(name = "per_city", columnDefinition = "VARCHAR(45)")
	private String perCity;
	@Column(name = "per_state", columnDefinition = "char(1)")
	private String perState;
	@Column(name = "per_zip", columnDefinition = "int(10)")
	private String perZip;

	@Column(name = "crsp_add1", columnDefinition = "VARCHAR(80)")
	private String crspAdd1;
	@Column(name = "crsp_add2", columnDefinition = "VARCHAR(80)")
	private String crspAdd2;
	@Column(name = "crsp_add3", columnDefinition = "VARCHAR(80)")
	private String crspAdd3;
	@Column(name = "crsp_city", columnDefinition = "VARCHAR(45)")
	private String crspCity;
	@Column(name = "crsp_state", columnDefinition = "CHAR(1)")
	private String crspState;
	@Column(name = "crsp_zip", columnDefinition = "int(11)")
	private String crspZip;
	@Column(name = "country", columnDefinition = "char(3)")
	private String country;

	@Column(name = "father_fname", columnDefinition = "VARCHAR(80)")
	private String fatherFirstName;
	@Column(name = "father_mname", columnDefinition = "VARCHAR(80)")
	private String fatherMiddleName;
	@Column(name = "father_lname", columnDefinition = "VARCHAR(80)")
	private String fatherLastName;

	@Column(name = "mother_fname", columnDefinition = "VARCHAR(80)")
	private String motheFirstName;
	@Column(name = "mother_mname", columnDefinition = "VARCHAR(80)")
	private String motherMiddleName;
	@Column(name = "mother_lname", columnDefinition = "VARCHAR(80)")
	private String motherLastname;
	@Column(name = "father_contact", columnDefinition = "VARCHAR(10)")
	private String fatherContact;
	@Column(name = "mother_contact", columnDefinition = "VARCHAR(80)")
	private String motherContact;

	@Column(name = "for_use_field1", columnDefinition = "VARCHAR(45)")
	private String useField1;
	@Column(name = "for_use_field2", columnDefinition = "VARCHAR(45)")
	private String useField2;
	@Column(name = "for_use_field3", columnDefinition = "VARCHAR(45)")
	private String useField3;
	@Column(name = "for_use_field4", columnDefinition = "VARCHAR(45)")
	private String useField4;
	@Column(name = "for_use_field5", columnDefinition = "VARCHAR(45)")
	private String useField5;
	@Column(name = "for_use_field6", columnDefinition = "VARCHAR(45)")
	private String useField6;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(8)")
	private String insertedBy;
	@Column(name = "inserted_time", columnDefinition = "datetime")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time", columnDefinition = "datetime")
	private Date updatedTime;
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
