package com.set.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_sportsactvty")
public class SportsActivity {


	@Id
	@GenericGenerator(name="incgenerator" , strategy="increment")
	@GeneratedValue(generator="incgenerator")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actvty_code", updatable = false, nullable = false)
	private int activityCode;	
	@Column(name = "actvty_description", columnDefinition = "VARCHAR(45)")
	private String activityDescription;
	@Column(name = "coach", columnDefinition = "VARCHAR(45)")
	private String coach;
	@Column(name = "is_active", columnDefinition = "int(1)")
	private int isActive;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;
	@Column(name = "class_id", columnDefinition = "VARCHAR(8)")
	private String classId;
	@Column(name = "section_id", columnDefinition = "VARCHAR(8)")
	private String sectionId;	
	@Column(name = "date")
	private Date date;
	
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
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
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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
	
	
}

