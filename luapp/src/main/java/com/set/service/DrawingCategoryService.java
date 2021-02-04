package com.set.service;

import java.util.List;

import com.set.model.DrawingCategory;
import com.set.model.DrawingCategoryDetails;

public interface DrawingCategoryService {
	public void save(DrawingCategory drawingCategory);

	public DrawingCategoryDetails getAllDrawingCategory(int pagenumber, String searchdata);

	public List<DrawingCategory> getAllDrawingCategorySelect();

	public DrawingCategory getDrawingCategoryById(String drawingCategoryId);

	public void updateDrawingCategory(DrawingCategory drawingCategory);

	public void deleteDrawingCategory(DrawingCategory drawingCategory);

	public boolean IsExist(String drawingCategory);

	public int totalDrawingCategory();

	public DrawingCategory getDrawingCategoryByName(String categoryName);
}
