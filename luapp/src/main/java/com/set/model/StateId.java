package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class StateId implements Serializable{
	@Column(name = "country_code", columnDefinition = "VARCHAR(4)")
	private String countryCode;
	@Column(name = "state_code", columnDefinition = "VARCHAR(4)")
	private String stateCode;
	public StateId() {
	}

	public StateId(String countryCode, String stateCode) {
		this.countryCode = countryCode;
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getStateCode() {
		return countryCode;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof StateId))
			return false;
		StateId that = (StateId) o;
		return Objects.equals(getCountryCode(), that.getCountryCode()) && Objects.equals(getStateCode(), that.getStateCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStateCode(), getCountryCode());
	}
}
