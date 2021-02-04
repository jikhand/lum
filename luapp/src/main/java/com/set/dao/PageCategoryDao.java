package com.set.dao;

import java.util.List;

import com.set.model.PageCategory;
import com.set.model.PageCategoryDetails;

public interface PageCategoryDao {
	
	public void save(PageCategory pageCategory);
	public void editPageCategory(PageCategory pageCategory); 
	public void deletePageCategory(PageCategory pageCategory); 
	PageCategoryDetails listPageCategory();
	public PageCategory getPageCategoryById(String id); 
	public List<PageCategory> listPageCategorySelect();
}
