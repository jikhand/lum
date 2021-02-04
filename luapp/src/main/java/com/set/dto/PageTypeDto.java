/**
 * 
 */
package com.set.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author set-0018
 *
 */
public class PageTypeDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3315295164081938939L;
	private String Id;
	private String ImageTypeName;

	@javax.persistence.Transient
	private String Message;

	public String getId() {
		return Id;
	}
	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public String getIageTypeName() {
		return ImageTypeName;
	}
	@JsonProperty("ImageTypeName")
	public void setIageTypeName(String iageTypeName) {
		ImageTypeName = iageTypeName;
	}

	public String getMessage() {
		return Message;
	}
	@JsonProperty("Message")
	public void setMessage(String message) {
		Message = message;
	}

	

}
