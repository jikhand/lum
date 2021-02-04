package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "lutbl_sports_announcement")
public class SportsAnnouncement {
	
	@Id
	@GenericGenerator(name="incgenerator" , strategy="increment")
	@GeneratedValue(generator="incgenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;	
	@Column(name = "title", columnDefinition = "VARCHAR(45)")
	private String title;
	@Column(name = "description", columnDefinition = "VARCHAR(255)")
	private String description;
	@Column(name = "match_timings", columnDefinition = "VARCHAR(255)")
	private String matchTimings;
	@Column(name = "last_date_for_entry", columnDefinition = "VARCHAR(255)")
	private String lastDateForEntry;
	@Column(name = "event_sponcered", columnDefinition = "VARCHAR(255)")
	private String eventSponcered;
	@Column(name = "contact_phone", columnDefinition = "VARCHAR(20)")
	private String contactPhone;
	@Column(name = "contact_email", columnDefinition = "VARCHAR(45)")
	private String contactEmail;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMatchTimings() {
		return matchTimings;
	}
	public void setMatchTimings(String matchTimings) {
		this.matchTimings = matchTimings;
	}
	public String getLastDateForEntry() {
		return lastDateForEntry;
	}
	public void setLastDateForEntry(String lastDateForEntry) {
		this.lastDateForEntry = lastDateForEntry;
	}
	public String getEventSponcered() {
		return eventSponcered;
	}
	public void setEventSponcered(String eventSponcered) {
		this.eventSponcered = eventSponcered;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}	
}
