package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.DrawingCategoryDao;
import com.set.model.DrawingCategory;
import com.set.model.DrawingCategoryDetails;
@Service
public class DrawingCategoryServiceImpl implements DrawingCategoryService {
	
	@Autowired
	public DrawingCategoryDao drawingCategoryDao;

	@Override
	public void save(DrawingCategory drawingCategory) {
		drawingCategoryDao.save(drawingCategory);
	}

	@Override
	public DrawingCategoryDetails getAllDrawingCategory(int pagenumber,String searchdata) {
		return drawingCategoryDao.getAllDrawingCategory(pagenumber, searchdata);
	}

	@Override
	public List<DrawingCategory> getAllDrawingCategorySelect() {
		return drawingCategoryDao.getAllDrawingCategorySelect();
	}
	
	@Override
	public void updateDrawingCategory(DrawingCategory drawingCategory) {
		drawingCategoryDao.updateDrawingCategory(drawingCategory);
	}

	@Override
	public void deleteDrawingCategory(DrawingCategory drawingCategory) {
		drawingCategoryDao.deleteDrawingCategory(drawingCategory);
	}

	@Override
	public boolean IsExist(String drawingCategory) {
		// TODO Auto-generated method stub
		return drawingCategoryDao.IsExist(drawingCategory);
	}

	@Override
	public int totalDrawingCategory() {
		return drawingCategoryDao.totalDrawingCategory();
	}

	@Override
	public DrawingCategory getDrawingCategoryById(String drawingCategoryId) {
		return drawingCategoryDao.getDrawingCategoryById(drawingCategoryId);
	}

	@Override
	public DrawingCategory getDrawingCategoryByName(String categoryName) {
		return drawingCategoryDao.getDrawingCategoryByName(categoryName);
	}



}
