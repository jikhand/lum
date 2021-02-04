package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "tbl_token")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserToken {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "id")
private Long  id;
private String  userid;
private String token; 
private Date expireat;
private Boolean isdeleted;
private Date createdat;

public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public Date getExpireat() {
	return expireat;
}
public void setExpireat(Date expireat) {
	this.expireat = expireat;
}
public Boolean getIsdeleted() {
	return isdeleted;
}
public void setIsdeleted(Boolean isdeleted) {
	this.isdeleted = isdeleted;
}
public Date getCreatedat() {
	return createdat;
}
public void setCreatedat(Date createdat) {
	this.createdat = createdat;
}
}
