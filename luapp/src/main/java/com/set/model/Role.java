package com.set.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecate")
@Entity
@Table(name="lutbl_role")
public class Role {

	   @Id
	   @Column(name="role_id",columnDefinition="VARCHAR(1)")
	   private String roleId;
	   @Column(name="role_desc",columnDefinition="VARCHAR(45)")
	   private String roleDescription;
	   
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	  	   
}