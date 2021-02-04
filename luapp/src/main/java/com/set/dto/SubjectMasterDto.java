package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectMasterDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 886560189258247416L;
	private String SubjectId;
	private String TextBookISBN;
	private String SubjectName;
	private String TextBookName;
	private String TextBookPath;
	private String TextBookLink;
	private String forUseField1;
	private String forUseField2;
	private int is_soft_delete;
	private String insertedBy;
	private Date insertedTime;
	private String updatedBy;
	private Date updatedTime;
	private String TypeOfMaterial;
	private String SubjectMaterialFiletype;
	private String SubjectCoverpage;

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getTextBookISBN() {
		return TextBookISBN;
	}

	@JsonProperty("TextBookISBN")
	public void setTextBookISBN(String textBookISBN) {
		TextBookISBN = textBookISBN;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getTextBookName() {
		return TextBookName;
	}
	@JsonProperty("TextBookName")
	public void setTextBookName(String textBookName) {
		TextBookName = textBookName;
	}

	public String getTextBookPath() {
		return TextBookPath;
	}
	@JsonProperty("TextBookPath")
	public void setTextBookPath(String textBookPath) {
		TextBookPath = textBookPath;
	}

	public String getTextBookLink() {
		return TextBookLink;
	}
	@JsonProperty("TextBookLink")
	public void setTextBookLink(String textBookLink) {
		TextBookLink = textBookLink;
	}

	public String getForUseField1() {
		return forUseField1;
	}
	public void setForUseField1(String forUseField1) {
		this.forUseField1 = forUseField1;
	}

	public String getForUseField2() {
		return forUseField2;
	}
	public void setForUseField2(String forUseField2) {
		this.forUseField2 = forUseField2;
	}

	public int getIs_soft_delete() {
		return is_soft_delete;
	}
	public void setIs_soft_delete(int is_soft_delete) {
		this.is_soft_delete = is_soft_delete;
	}

	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getTypeOfMaterial() {
		return TypeOfMaterial;
	}
	@JsonProperty("TypeOfMaterial")
	public void setTypeOfMaterial(String typeOfMaterial) {
		TypeOfMaterial = typeOfMaterial;
	}

	public String getSubjectMaterialFiletype() {
		return SubjectMaterialFiletype;
	}
	@JsonProperty("SubjectMaterialFiletype")
	public void setSubjectMaterialFiletype(String subjectMaterialFiletype) {
		SubjectMaterialFiletype = subjectMaterialFiletype;
	}

	public String getSubjectCoverpage() {
		return SubjectCoverpage;
	}
	@JsonProperty("SubjectCoverpage")
	public void setSubjectCoverpage(String subjectCoverpage) {
		SubjectCoverpage = subjectCoverpage;
	}
}
