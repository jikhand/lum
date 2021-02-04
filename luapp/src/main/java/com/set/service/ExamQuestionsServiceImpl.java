package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.ExamQuestionsDao;
import com.set.dao.NotesMasterDao;
import com.set.model.Country;
import com.set.model.CountryDetails;
import com.set.model.ExamQuestions;
import com.set.model.ExamQuestionsId;
import com.set.model.NotesMaster;
@Service
public class ExamQuestionsServiceImpl implements ExamQuestionsService {
	
	@Autowired
	public ExamQuestionsDao examQuestionsDao;

	@Override
	public void save(ExamQuestions examQuestions) {
		examQuestionsDao.save(examQuestions);
	}

	@Override
	public List<ExamQuestions> getAllExamQuestionsSelect() {
		return examQuestionsDao.getAllExamQuestionsSelect();
	}
	
	@Override
	public void updateExamQuestions(ExamQuestions examQuestions) {
		examQuestionsDao.updateExamQuestions(examQuestions);
	}

	@Override
	public void deleteExamQuestions(ExamQuestionsId examQuestionsId) {
		examQuestionsDao.deleteExamQuestions(examQuestionsId);
	}

	@Override
	public boolean IsExist(String examQuestions) {
		// TODO Auto-generated method stub
		return examQuestionsDao.IsExist(examQuestions);
	}

	@Override
	public int totalExamQuestions() {
		return examQuestionsDao.totalExamQuestions();
	}

	@Override
	public ExamQuestions getExamQuestionsById(String examQuestionsId) {
		return examQuestionsDao.getExamQuestionsById(examQuestionsId);
	}



}
