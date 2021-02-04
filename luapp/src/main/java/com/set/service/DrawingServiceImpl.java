package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.DrawingDao;
import com.set.dao.NotesMasterDao;
import com.set.model.ClassSubject;
import com.set.model.Country;
import com.set.model.CountryDetails;
import com.set.model.Drawing;
import com.set.model.DrawingDetails;
import com.set.model.DrawingListDetails;
import com.set.model.NotesMaster;
@Service
public class DrawingServiceImpl implements DrawingService {
	
	@Autowired
	public DrawingDao drawingDao;

	@Override
	public void save(Drawing drawing) {
		drawingDao.save(drawing);
	}

	@Override
	public DrawingDetails getAllDrawing(int pagenumber,String searchdata) {
		return drawingDao.getAllDrawing(pagenumber, searchdata);
	}

	@Override
	public List<Drawing> getAllDrawingSelect() {
		return drawingDao.getAllDrawingSelect();
	}
	
	@Override
	public void updateDrawing(Drawing drawing) {
		drawingDao.updateDrawing(drawing);
	}

	@Override
	public void deleteDrawing(Drawing drawing) {
		drawingDao.deleteDrawing(drawing);
	}

	@Override
	public boolean IsExist(String drawing) {
		// TODO Auto-generated method stub
		return drawingDao.IsExist(drawing);
	}

	@Override
	public int totalDrawing() {
		return drawingDao.totalDrawing();
	}

	@Override
	public Drawing getDrawingById(String drawingId) {
		return drawingDao.getDrawingById(drawingId);
	}

	@Override
	public List<Object[]> getAllStudentDrawings(String studentId) {
		return drawingDao.getAllStudentDrawings(studentId);
	}

	@Override
	public List<Object[]> getAllStudentDrawingCategoryName(String studentId, String userId) {
		return drawingDao.getAllStudentDrawingCategoryName(studentId, userId);
	}

	@Override
	public List<Object[]> getAllStudentDrawingCategory(String studentId,String userId ,String drawingCategoryId) {
		return drawingDao.getAllStudentDrawingCategory(studentId,userId ,drawingCategoryId);
	}

	@Override
	public DrawingListDetails getAllStudentDrawingList(int pagenumber, String searchdata, String studentId,String ClassSubject) {
		return drawingDao.getAllStudentDrawingList(pagenumber, searchdata, studentId,ClassSubject);
	}
	public String getClassSubject(String UserId) {
		return drawingDao.getClassSubject(UserId);
	}
}
