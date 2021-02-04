package com.set.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_stdntactvty")
public class StudentActivity {

	@Id
	@EmbeddedId
	public StudentActivityId studentActivityId;

	@Column(name = "enrolled_date")
	private Date enrolledDate;
	@Column(name = "deactivate_date")
	private Date deactivateDate;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;

	@Transient
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StudentActivityId getStudentActivityId() {
		return studentActivityId;
	}

	public void setStudentActivityId(StudentActivityId studentActivityId) {
		this.studentActivityId = studentActivityId;
	}

	public Date getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public Date getDeactivateDate() {
		return deactivateDate;
	}

	public void setDeactivateDate(Date deactivateDate) {
		this.deactivateDate = deactivateDate;
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


}
