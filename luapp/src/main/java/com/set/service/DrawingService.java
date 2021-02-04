package com.set.service;

import java.util.List;
import java.util.Map;

import com.set.model.ClassSubject;
import com.set.model.Drawing;
import com.set.model.DrawingDetails;
import com.set.model.DrawingListDetails;

public interface DrawingService {
	  public void save(Drawing drawing);
	  public DrawingDetails getAllDrawing(int pagenumber,String searchdata);
	  public DrawingListDetails getAllStudentDrawingList(int pagenumber,String searchdata,String studentId,String ClassSubject);
	  public List<Drawing> getAllDrawingSelect();
	  public Drawing getDrawingById(String drawingId);
	  public void updateDrawing(Drawing drawing);
	  public void deleteDrawing(Drawing drawing);
	  public boolean IsExist(String drawing);
	  public int totalDrawing();
	  public String getClassSubject(String UserId);
	  public List<Object[]> getAllStudentDrawings(String studentId);
	  public List<Object[]> getAllStudentDrawingCategoryName(String studentId, String userId);
	  public List<Object[]> getAllStudentDrawingCategory(String studentId,String userId ,String drawingCategoryId);
}
