package com.set.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "lutbl_notifyread")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotifyRead{
	@Id
    @EmbeddedId	   
    private NotifyReadId NotifyReadId;
   
    
    @Column(name = "is_read", columnDefinition = "int(1) default 0")
	private int isRead;    
    @Column(name = "read_on")
	private Date readOn;    
    @javax.persistence.Transient
	private String message;
	
    
	public NotifyReadId getNotifyReadId() {
		return NotifyReadId;
	}
	public void setNotifyReadId(NotifyReadId notifyReadId) {
		NotifyReadId = notifyReadId;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public Date getReadOn() {
		return readOn;
	}
	public void setReadOn(Date readOn) {
		this.readOn = readOn;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
