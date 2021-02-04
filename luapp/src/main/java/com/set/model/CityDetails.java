package com.set.model;

import java.io.Serializable;
import java.util.List;

public class CityDetails implements Serializable{
	private static final long serialVersionUID = 4436302371793843798L;
	
	private List<City> city;
	public List<City> getCity() {
		return city;
	}
	public void setCity(List<City> city) {
		this.city = city;
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
