package com.set.model;

import java.sql.Timestamp;
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
import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_passwd")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", columnDefinition = "VARCHAR(8)")
	private String userId;
	@Column(name = "user_org_id", columnDefinition = "VARCHAR(8)")
	private String userOrganisationId;
	@Column(name = "email_id", columnDefinition = "VARCHAR(100)")
	private String emailId;
	@Column(name = "curr_passwd", columnDefinition = "VARCHAR(500)")
	private String currentPassword;
	@Column(name = "last_change_date")
	private Date passwordLastChangeDate;
	@Column(name = "prv_change1", columnDefinition = "VARCHAR(500)")
	private String previousChange1;
	@Column(name = "prv_change2", columnDefinition = "VARCHAR(500)")
	private String previousChange2;
	@Column(name = "prv_change3", columnDefinition = "VARCHAR(500)")
	private String previousChange3;
	@Column(name = "prv_change4", columnDefinition = "VARCHAR(500)")
	private String previousChange4;
	@Column(name = "prv_change5", columnDefinition = "VARCHAR(500)")
	private String previousChange5;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserOrganisationId() {
		return userOrganisationId;
	}

	public void setUserOrganisationId(String userOrganisationId) {
		this.userOrganisationId = userOrganisationId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public Date getPasswordLastChangeDate() {
		return passwordLastChangeDate;
	}

	public void setPasswordLastChangeDate(Date passwordLastChangeDate) {
		this.passwordLastChangeDate = passwordLastChangeDate;
	}

	public String getPreviousChange1() {
		return previousChange1;
	}

	public void setPreviousChange1(String previousChange1) {
		this.previousChange1 = previousChange1;
	}

	public String getPreviousChange2() {
		return previousChange2;
	}

	public void setPreviousChange2(String previousChange2) {
		this.previousChange2 = previousChange2;
	}

	public String getPreviousChange3() {
		return previousChange3;
	}

	public void setPreviousChange3(String previousChange3) {
		this.previousChange3 = previousChange3;
	}

	public String getPreviousChange4() {
		return previousChange4;
	}

	public void setPreviousChange4(String previousChange4) {
		this.previousChange4 = previousChange4;
	}

	public String getPreviousChange5() {
		return previousChange5;
	}

	public void setPreviousChange5(String previousChange5) {
		this.previousChange5 = previousChange5;
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