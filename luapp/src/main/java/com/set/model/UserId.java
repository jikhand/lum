package com.set.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.SerializableString;

public class UserId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2959599179772574326L;
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
