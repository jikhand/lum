package com.set.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "lutbl_state")
public class State {
	@javax.persistence.Transient
	private String message;
	@javax.persistence.Transient
	private long tempCountryId;
	public long getTempCountryId() {
		return tempCountryId;
	}

	public void setTempCountryId(long tempCountryId) {
		this.tempCountryId = tempCountryId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="state_id")
	private long stateId;
	
	@Column(name="state_name")
	private String stateName;
	
	
	@ManyToOne(targetEntity = Country.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="CountryId")
	
	private Country country;
	
	@Column(name="is_deleted")
	private Integer isDeleted;
	
	@Column(name="created_at")
	private Date createdAt;
	
	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}