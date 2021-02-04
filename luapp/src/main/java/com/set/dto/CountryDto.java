package com.set.dto;

import java.io.Serializable;

public class CountryDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2429559948771427744L;
	private String countryCode;
	private String countryName;
	@javax.persistence.Transient
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
