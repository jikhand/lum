package com.set.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_srlog")
public class ServiceRequest {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "sr_id", columnDefinition = "int(20)")
	private int serviceRequestId;
	@Column(name = "sub_category_id", columnDefinition = "VARCHAR(16)")
	private String subCategoryId;
	@Column(name = "requestor_id", columnDefinition = "VARCHAR(16)")
	private String requestorId;
	@Column(name = "sr_title", columnDefinition = "VARCHAR(45)")
	private String serviceRequestTitle;
	@Column(name = "raised_on")
	private Date raisedOn;
	@Column(name = "sr_closedon")
	private Date serviceRequestClosedOn;
	@Column(name = "assigned_to", columnDefinition = "VARCHAR(45)")
	private String assignedTo;
	@Column(name = "status", columnDefinition = "VARCHAR(1)")
	private String status;
	@Column(name = "details", columnDefinition = "VARCHAR(255)")
	private String details;
	@Column(name = "details_attached", columnDefinition = "VARCHAR(255)")
	private String detailsAttached;
	@Column(name = "extension", columnDefinition = "VARCHAR(45)")
	private String extension;
	@Column(name = "remarks", columnDefinition = "VARCHAR(255)")
	private String remarks;
	@Column(name = "is_soft_delete", columnDefinition = "int(1)")
	private int isSoftDelete;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;
	@Column(name ="comment",columnDefinition = "VARCHAR(255)")
	private Date comment;
	 
    public Date getComment() {
		return comment;
	}

	public void setComment(Date comment) {
		this.comment = comment;
	}

	@Transient
    private String message;
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(int serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public String getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public String getServiceRequestTitle() {
		return serviceRequestTitle;
	}

	public void setServiceRequestTitle(String serviceRequestTitle) {
		this.serviceRequestTitle = serviceRequestTitle;
	}

	public Date getRaisedOn() {
		return raisedOn;
	}

	public void setRaisedOn(Date raisedOn) {
		this.raisedOn = raisedOn;
	}

	public Date getServiceRequestClosedOn() {
		return serviceRequestClosedOn;
	}

	public void setServiceRequestClosedOn(Date serviceRequestClosedOn) {
		this.serviceRequestClosedOn = serviceRequestClosedOn;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetailsAttached() {
		return detailsAttached;
	}

	public void setDetailsAttached(String detailsAttached) {
		this.detailsAttached = detailsAttached;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getIsSoftDelete() {
		return isSoftDelete;
	}

	public void setIsSoftDelete(int isSoftDelete) {
		this.isSoftDelete = isSoftDelete;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

		
}
