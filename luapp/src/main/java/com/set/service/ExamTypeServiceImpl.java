package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.ExamTypeDao;
import com.set.model.ExamType;
import com.set.model.ExamTypeDetails;
@Service
public class ExamTypeServiceImpl implements ExamTypeService {
	
	@Autowired
	public ExamTypeDao examTypeDao;

	@Override
	public void save(ExamType examType) {
		examTypeDao.save(examType);
	}

	@Override
	public ExamTypeDetails getAllExamType() {
		return examTypeDao.getAllExamType();
	}

	@Override
	public List<ExamType> getAllExamTypeSelect() {
		return examTypeDao.getAllExamTypeSelect();
	}
	
	@Override
	public void updateExamType(ExamType examType) {
		examTypeDao.updateExamType(examType);
	}

	@Override
	public void deleteExamType(ExamType examType) {
		examTypeDao.deleteExamType(examType);
	}

	@Override
	public boolean IsExist(String examType) {
		// TODO Auto-generated method stub
		return examTypeDao.IsExist(examType);
	}

	@Override
	public int totalExamType() {
		return examTypeDao.totalExamType();
	}

	@Override
	public ExamType getExamTypeById(String examTypeId) {
		return examTypeDao.getExamTypeById(examTypeId);
	}



}
