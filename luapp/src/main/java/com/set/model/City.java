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
@Table(name = "lutbl_city")
public class City {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="city_id")
	//columnDefinition = "serial",
	private long cityId;
	@Column(name="city_name")
	private String cityName;
	
	@javax.persistence.Transient
	private long tempStateId;
	
	@ManyToOne(targetEntity = State.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="state_id")
	private State stateMaster;
	
	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public State getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(State stateMaster) {
		this.stateMaster = stateMaster;
	}
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
