package com.set.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "lutbl_page_category")
public class PageCategory {
	@Id
	@GenericGenerator(name="incgenerator" , strategy="increment")
	@GeneratedValue(generator="incgenerator")
	@Column(name = "page_category_id", columnDefinition = "int(10)")
	private int pageCategoryId;
	
	
	@Column(name = "page_category_type_name", columnDefinition = "VARCHAR(50)")
	private String pageCategoryTypeName;
		
	@javax.persistence.Transient
	private String message;

	public int getPageCategoryId() {
		return pageCategoryId;
	}

	public void setPageCategoryId(int pageCategoryId) {
		this.pageCategoryId = pageCategoryId;
	}

	

	public String getPageCategoryTypeName() {
		return pageCategoryTypeName;
	}

	public void setPageCategoryTypeName(String pageCategoryTypeName) {
		this.pageCategoryTypeName = pageCategoryTypeName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
