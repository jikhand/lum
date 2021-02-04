package com.set.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.beans.Transient;
@Entity
@Table(name = "lutbl_country")
public class Country {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="country_id")
	private long countryId;
	
	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name="country_name")
	private String countryName;
	
}