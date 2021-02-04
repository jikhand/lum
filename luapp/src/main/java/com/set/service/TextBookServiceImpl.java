package com.set.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.TextBookDao;
import com.set.dto.TextBookListDto;
import com.set.model.SportsActivityDetails;
import com.set.model.TextBookDetails;
import com.set.model.TextBookMaster;
@Service
public class TextBookServiceImpl implements TextBookService {
	
	@Autowired
	public TextBookDao textBookDao;

	@Override
	public void save(TextBookMaster textBookMaster) {
		textBookDao.save(textBookMaster);
	}
	
	@Override
	public TextBookListDto getAllTextBooksByClassId(String classId) {
		// TODO Auto-generated method stub
		return textBookDao.getAllTextBooksByClassId(classId);
	}
	@Override
	public TextBookListDto getAllTextBooksByClassIdSectionId(String classId, String sectionId) {
		// TODO Auto-generated method stub
		return textBookDao.getAllTextBooksByClassIdSectionId(classId, sectionId);
	}
	
	@Override
	public TextBookListDto getAllTextBooksByTeacherId(String teacherId) {
		// TODO Auto-generated method stub
		return textBookDao.getAllTextBooksByTeacherId(teacherId);
	}
	
	@Override
	public TextBookDetails textBookSearch(int pageNumber,String searchData) {
		return textBookDao.textBookSearch(pageNumber, searchData);
	}
}
