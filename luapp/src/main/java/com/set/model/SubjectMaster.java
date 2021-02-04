package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_subject_master")
public class SubjectMaster {
	   @EmbeddedId
       private SubjectMasterId subjectMasterId;
	   public SubjectMasterId getSubjectMasterId() {
		return subjectMasterId;
	   }
		public void setSubjectMasterId(SubjectMasterId subjectMasterId) {
			this.subjectMasterId = subjectMasterId;
		}
	@Column(name="subject_name",columnDefinition="VARCHAR(80)")
	   private String subjectName;
	   @Column(name="text_book_name",columnDefinition="VARCHAR(45)")
	   private String textBookName;
	   @Column(name="text_book_path",columnDefinition="VARCHAR(240)")
	   private String textBookPath;
	   @Column(name="text_book_link",columnDefinition="VARCHAR(240)")
	   private String textBookLink;
	   @Column(name="for_use_field1",columnDefinition="VARCHAR(45)")
	   private String forUseField1;
	   @Column(name="for_use_field2",columnDefinition="VARCHAR(45)")
	   private String forUseField2;
	   @Column(name="is_soft_delete",columnDefinition="int(1)")
	   private int is_soft_delete;
	   @Column(name="inserted_by",columnDefinition="VARCHAR(45)")
	   private String insertedBy;
	   @Column(name="inserted_time")
	   private Date insertedTime;
	   @Column(name="updated_by",columnDefinition="VARCHAR(45)")
	   private String updatedBy;
	   @Column(name="updated_time")
	   private Date updatedTime;
	   @Column(name="typ_of_material",columnDefinition="VARCHAR(1)")
	   private String typeOfMaterial;
	   @Column(name="sub_material_filetype",columnDefinition="VARCHAR(45)")
	   private String subjectMaterialFiletype;
	   @Column(name="subject_coverpage",columnDefinition="VARCHAR(45)")
	   private String subjectCoverpage;
	   
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getTextBookName() {
		return textBookName;
	}
	public void setTextBookName(String textBookName) {
		this.textBookName = textBookName;
	}
	public String getTextBookPath() {
		return textBookPath;
	}
	public void setTextBookPath(String textBookPath) {
		this.textBookPath = textBookPath;
	}
	public String getTextBookLink() {
		return textBookLink;
	}
	public void setTextBookLink(String textBookLink) {
		this.textBookLink = textBookLink;
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
		return typeOfMaterial;
	}
	public void setTypeOfMaterial(String typeOfMaterial) {
		this.typeOfMaterial = typeOfMaterial;
	}
	public String getSubjectMaterialFiletype() {
		return subjectMaterialFiletype;
	}
	public void setSubjectMaterialFiletype(String subjectMaterialFiletype) {
		this.subjectMaterialFiletype = subjectMaterialFiletype;
	}
	public String getSubjectCoverpage() {
		return subjectCoverpage;
	}
	public void setSubjectCoverpage(String subjectCoverpage) {
		this.subjectCoverpage = subjectCoverpage;
	}
	@Transient
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	   
}
