package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lutbl_libreq")
public class LibraryRequest {
	@EmbeddedId
	private LibraryRequestId libraryRequestId;
	@Column(name = "book_isbn", columnDefinition = "VARCHAR(25)")
	private String bookIsbn;
	
	@Column(name = "title", columnDefinition = "VARCHAR(45)")
	private String title;
	
	@Column(name = "request_on")
	private Date requestOn;
	
	@Column(name = "issued_on")
	private Date issuedOn;
	
	@Column(name = "expected_return")
	private Date expectedReturn;
	
	@Column(name = "return_on")
	private Date returnOn;
	
	@Column(name = "renewed_on")
	private Date renewedOn;
	
	@Column(name = "no_of_renewals", columnDefinition = "int(2)")
	private int noOfRenewals;
	
	@Column(name = "remarks", columnDefinition = "VARCHAR(45)")
	private String remarks;
	
	@Column(name = "dues_charged", columnDefinition = "FLOAT(4,2)")
	private float duesCharged;
	
	@Column(name = "dues_paid", columnDefinition = "FLOAT(4,2)")
	private float duesPaid;
	
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	
	@Column(name = "inserted_time")
	private Date insertedTime;
	
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	@Transient
	private String message;	

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRequestOn() {
		return requestOn;
	}

	public void setRequestOn(Date requestOn) {
		this.requestOn = requestOn;
	}

	public Date getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(Date issuedOn) {
		this.issuedOn = issuedOn;
	}

	public Date getExpectedReturn() {
		return expectedReturn;
	}

	public void setExpectedReturn(Date expectedReturn) {
		this.expectedReturn = expectedReturn;
	}

	public Date getReturnOn() {
		return returnOn;
	}

	public void setReturnOn(Date returnOn) {
		this.returnOn = returnOn;
	}

	public Date getRenewedOn() {
		return renewedOn;
	}

	public void setRenewedOn(Date renewedOn) {
		this.renewedOn = renewedOn;
	}

	public int getNoOfRenewals() {
		return noOfRenewals;
	}

	public void setNoOfRenewals(int noOfRenewals) {
		this.noOfRenewals = noOfRenewals;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public float getDuesCharged() {
		return duesCharged;
	}

	public void setDuesCharged(float duesCharged) {
		this.duesCharged = duesCharged;
	}

	public float getDuesPaid() {
		return duesPaid;
	}

	public void setDuesPaid(float duesPaid) {
		this.duesPaid = duesPaid;
	}

	public LibraryRequestId getLibraryRequestId() {
		return libraryRequestId;
	}

	public void setLibraryRequestId(LibraryRequestId libraryRequestId) {
		this.libraryRequestId = libraryRequestId;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
