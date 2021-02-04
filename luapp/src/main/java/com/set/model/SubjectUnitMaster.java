package com.set.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonProperty;
@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_subj_unit_mstr")
public class SubjectUnitMaster {

	@EmbeddedId
	private SubjectUnitMasterId SubjectUnitMasterId;
	@Column(name = "subj_unit_key", columnDefinition = "VARCHAR(45)")
	private String SubjectUnitKey;
	@Column(name = "topic_name", columnDefinition = "VARCHAR(80)")
	private String TopicName;
	@Column(name = "topic_material_filetype", columnDefinition = "VARCHAR(45)")
	private String TopicMaterialFiletype;
	@Column(name = "topic_book_path", columnDefinition = "VARCHAR(240)")
	private String TopicBookPath;
	@Column(name = "topic_book_link", columnDefinition = "VARCHAR(240)")
	private String TopicBookLink;
	@Column(name = "typ_of_material", columnDefinition = "VARCHAR(1)")
	private String TypeOfMaterial;
	@Column(name = "for_use_field1", columnDefinition = "VARCHAR(45)")
	private String forUseField1;
	@Column(name = "for_use_field2", columnDefinition = "VARCHAR(45)")
	private String forUseField2;
	@Column(name = "is_soft_delete", columnDefinition = "int(1)")
	private int is_soft_delete;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;
	@Transient
	private String message;

	public SubjectUnitMasterId getSubjectUnitMasterId() {
		return SubjectUnitMasterId;
	}

	public String getSubjectUnitKey() {
		return SubjectUnitKey;
	}

	public String getTopicName() {
		return TopicName;
	}

	public String getTopicMaterialFiletype() {
		return TopicMaterialFiletype;
	}

	public String getTopicBookPath() {
		return TopicBookPath;
	}

	public String getTopicBookLink() {
		return TopicBookLink;
	}

	public String getTypeOfMaterial() {
		return TypeOfMaterial;
	}

	public String getForUseField1() {
		return forUseField1;
	}

	public String getForUseField2() {
		return forUseField2;
	}

	public int getIs_soft_delete() {
		return is_soft_delete;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public String getMessage() {
		return message;
	}

	@JsonProperty("SubjectUnitMasterId")
	public void setSubjectUnitMasterId(SubjectUnitMasterId subjectUnitMasterId) {
		this.SubjectUnitMasterId = subjectUnitMasterId;
	}

	@JsonProperty("SubjectUnitKey")
	public void setSubjectUnitKey(String subjectUnitKey) {
		this.SubjectUnitKey = subjectUnitKey;
	}

	@JsonProperty("TopicName")
	public void setTopicName(String topicName) {
		this.TopicName = topicName;
	}

	@JsonProperty("TopicMaterialFiletype")
	public void setTopicMaterialFiletype(String topicMaterialFiletype) {
		this.TopicMaterialFiletype = topicMaterialFiletype;
	}

	@JsonProperty("TopicBookPath")
	public void setTopicBookPath(String topicBookPath) {
		this.TopicBookPath = topicBookPath;
	}

	@JsonProperty("TopicBookLink")
	public void setTopicBookLink(String topicBookLink) {
		this.TopicBookLink = topicBookLink;
	}

	@JsonProperty("TypeOfMaterial")
	public void setTypeOfMaterial(String typeOfMaterial) {
		this.TypeOfMaterial = typeOfMaterial;
	}

	public void setForUseField1(String forUseField1) {
		this.forUseField1 = forUseField1;
	}

	public void setForUseField2(String forUseField2) {
		this.forUseField2 = forUseField2;
	}

	public void setIs_soft_delete(int is_soft_delete) {
		this.is_soft_delete = is_soft_delete;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
