package com.set.model;

import java.io.Serializable;
import java.util.List;

public class CountryDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843793L;
	private List<Country> country;
	public List<Country> getCountry() {
		return country;
	}
	public void setCountry(List<Country> country) {
		this.country = country;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private int count;
	@javax.persistence.Transient
	private String message;
}