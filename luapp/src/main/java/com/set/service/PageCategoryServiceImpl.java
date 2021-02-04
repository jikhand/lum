package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.PageCategoryDao;
import com.set.model.PageCategory;
import com.set.model.PageCategoryDetails;

@Service
public class PageCategoryServiceImpl implements PageCategoryService {

	@Autowired
	public PageCategoryDao pageCategoryDao;

	@Override
	public void save(PageCategory pageCategory) {
		pageCategoryDao.save(pageCategory);
	}
	
	@Override
	public void editPageCategory(PageCategory pageCategory) {
		pageCategoryDao.editPageCategory(pageCategory);

	}

	@Override
	public void deletePageCategory(PageCategory pageCategory) {
		pageCategoryDao.deletePageCategory(pageCategory);
	}
	
	@Override
	public PageCategoryDetails listPageCategory() {
		// TODO Auto-generated method stub
		return pageCategoryDao.listPageCategory();
	}
	@Override
	public List<PageCategory> listPageCategorySelect() {
		return pageCategoryDao.listPageCategorySelect();
	}
	@Override
	public PageCategory getPageCategoryById(String id) {
		return pageCategoryDao.getPageCategoryById(id);
	}
}
