package com.set.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "tbllogs")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TblLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	public String getElementId() {
		return ElementId;
	}

	public void setElementId(String elementId) {
		ElementId = elementId;
	}

	private String ElementId;

	private String ActionType;
	private String ActionPerformedById;

	public String getActionPerformedById() {
		return ActionPerformedById;
	}

	public void setActionPerformedById(String actionPerformedById) {
		ActionPerformedById = actionPerformedById;
	}

	private String ActionElement;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Timestamp CreatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionType() {
		return ActionType;
	}

	public void setActionType(String actionType) {
		ActionType = actionType;
	}

	public String getActionElement() {
		return ActionElement;
	}

	public void setActionElement(String actionElement) {
		ActionElement = actionElement;
	}

	public Timestamp getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		CreatedAt = createdAt;
	}
	// @Override
	// public String toString() {
	// // TODO Auto-generated method stub
	// return "UserToken [tokenid :"+tokenid+",token:"+token+"]";
	// }
}