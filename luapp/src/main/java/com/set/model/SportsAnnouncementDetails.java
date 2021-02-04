package com.set.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SportsAnnouncementDetails implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -45386582182466916L;

	private List<SportsAnnouncement> SportsAnnouncements;
	
	public List<SportsAnnouncement> getSportsAnnouncements() {
		return SportsAnnouncements;
	}
	@JsonProperty("SportsAnnouncements")
	public void setSportsAnnouncements(List<SportsAnnouncement> SportsAnnouncements) {
		this.SportsAnnouncements = SportsAnnouncements;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	private int count;
	
	@javax.persistence.Transient
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}